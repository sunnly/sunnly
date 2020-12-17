package wang.sunnly.test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * CosineSimilarAlgorithm
 *
 * @author Sunnly
 * @since 2020/11/29 0029
 */
public class CosineSimilarAlgorithm {

    private static final int BUFFER_LEN = 2;
    public static double getSimilarity(String doc1, String doc2) {
        if (doc1 != null && doc1.trim().length() > 0 && doc2 != null
                && doc2.trim().length() > 0) {

            Map<Integer, int[]> algorithmMap = new HashMap<Integer, int[]>(16);

            //将两个字符串中的中文字符以及出现的总数封装到，AlgorithmMap中
            for (int i = 0; i < doc1.length(); i++) {
                char d1 = doc1.charAt(i);
                if (isHanZi(d1)) {
                    int charIndex = getGb2312Id(d1);
                    if (charIndex != -1) {
                        int[] fq = algorithmMap.get(charIndex);
                        if (fq != null && fq.length == 2) {
                            fq[0]++;
                        } else {
                            fq = new int[2];
                            fq[0] = 1;
                            fq[1] = 0;
                            algorithmMap.put(charIndex, fq);
                        }
                    }
                }
            }

            for (int i = 0; i < doc2.length(); i++) {
                char d2 = doc2.charAt(i);
                if (isHanZi(d2)) {
                    int charIndex = getGb2312Id(d2);
                    if (charIndex != -1) {
                        int[] fq = algorithmMap.get(charIndex);
                        if (fq != null && fq.length == 2) {
                            fq[1]++;
                        } else {
                            fq = new int[2];
                            fq[0] = 0;
                            fq[1] = 1;
                            algorithmMap.put(charIndex, fq);
                        }
                    }
                }
            }

            Iterator<Integer> iterator = algorithmMap.keySet().iterator();
            double sqdoc1 = 0;
            double sqdoc2 = 0;
            double denominator = 0;
            while (iterator.hasNext()) {
                int[] c = algorithmMap.get(iterator.next());
                denominator += c[0] * c[1];
                sqdoc1 += c[0] * c[0];
                sqdoc2 += c[1] * c[1];
            }

            return denominator / Math.sqrt(sqdoc1 * sqdoc2);
        } else {
            throw new NullPointerException(
                    " the Document is null or have not cahrs!!");
        }
    }

    public static boolean isHanZi(char ch) {
        // 判断是否汉字
        return (ch >= 0x4E00 && ch <= 0x9FA5);

    }

    /**
     * 根据输入的Unicode字符，获取它的GB2312编码或者ascii编码，
     *
     * @param ch 输入的GB2312中文字符或者ASCII字符(128个)
     * @return ch在GB2312中的位置，-1表示该字符不认识
     */
    public static short getGb2312Id(char ch) {
        try {
            byte[] buffer = Character.toString(ch).getBytes("GB2312");
            if (buffer.length != BUFFER_LEN) {
                // 正常情况下buffer应该是两个字节，否则说明ch不属于GB2312编码，故返回'?'，此时说明不认识该字符
                return -1;
            }
            // 编码从A1开始，因此减去0xA1=161
            int b0 = (buffer[0] & 0x0FF) - 161;
            // 第一个字符和最后一个字符没有汉字，因此每个区只收16*6-2=94个汉字
            int b1 = (buffer[1] & 0x0FF) - 161;
            return (short) (b0 * 94 + b1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {

        System.out.println(CosineSimilarAlgorithm.getSimilarity("你是谁，大家好", "大家好，我是谁"));

    }
}
