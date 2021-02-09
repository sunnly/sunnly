package wang.sunnly.modules.admin.rule;

import org.junit.Test;
import wang.sunnly.modules.admin.domain.rule.Condition;
import wang.sunnly.modules.admin.domain.rule.Option;
import wang.sunnly.modules.admin.domain.rule.Rules;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * RuleTest
 *
 * @author Sunnly
 * @since 2020/12/23
 */
public class RuleTest {

    @Test
    public void string2Rules(){
//        String rules = "{\"value\":[{\"filed\": \"column_1\",\"conditions\": \"EQ\",\"value\": \"aaa\",\"myType\": \"STRING_myType\"},{\"filed\": \"column_1\",\"conditions\": \"LIKE\",\"value\": \"aaa\",\"myType\": \"STRING_myType\",\"op\": \"AND\"},{\"filed\": \"column_1\",\"conditions\": \"LESS\",\"value\": \"aaa\",\"myType\": \"DATE_myType\",\"op\": \"OR\"},{\"value\": [{\"filed\": \"column_1\",\"conditions\": \"EQ\",\"value\": \"aaa\",\"myType\": \"STRING_myType\"},{\"filed\": \"column_1\",\"conditions\": \"LIKE\",\"value\": \"aaa\",\"myType\": \"STRING_myType\",\"op\": \"AND\"}],\"myType\": \"GROUP_myType\",\"op\": \"AND\"}],\"myType\":\"GROUP_myType\",\"op\": \"AND\"}";
        String rules = "{\n" +
                "\t\"value\": [{\n" +
                "\t\t\"filed\": \"column_1\",\n" +
                "\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\"value\": \"aaa\",\n" +
                "\t\t\"myType\": \"STRING_myType\"\n" +
                "\t}, {\n" +
                "\t\t\"filed\": \"column_1\",\n" +
                "\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\"value\": \"aaa\",\n" +
                "\t\t\"myType\": \"STRING_myType\",\n" +
                "\t\t\"op\": \"AND\"\n" +
                "\t}, {\n" +
                "\t\t\"filed\": \"column_1\",\n" +
                "\t\t\"conditions\": \"LESS\",\n" +
                "\t\t\"value\": \"aaa\",\n" +
                "\t\t\"myType\": \"DATE_myType\",\n" +
                "\t\t\"op\": \"OR\"\n" +
                "\t}, {\n" +
                "\t\t\"value\": [{\n" +
                "\t\t\t\"filed\": \"column_1\",\n" +
                "\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\"value\": \"aaa\",\n" +
                "\t\t\t\"myType\": \"STRING_myType\"\n" +
                "\t\t}, {\n" +
                "\t\t\t\"filed\": \"column_1\",\n" +
                "\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\"value\": \"aaa\",\n" +
                "\t\t\t\"myType\": \"STRING_myType\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t}],\n" +
                "\t\t\"myType\": \"GROUP_myType\",\n" +
                "\t\t\"op\": \"AND\"\n" +
                "\t}],\n" +
                "\t\"myType\": \"GROUP_myType\",\n" +
                "\t\"op\": \"AND\"\n" +
                "}";
//        String rules = "[{\"name\":\"JSON\",\"address\":\"北京市西城区\",\"age\":25}]";
//        Object parse = JSON.parse(rules);
        Rules rules1 = Rules.getRules(rules);
        System.out.println(rules1);
//        System.out.println(parse);
    }

    @Test
    public void getSmipleString(){
        Rules rules = new Rules();
        rules.setOp(Option.AND);
        rules.setFiled("column_1");
//        rules.setType(Type.STRING_TYPE);
        rules.setConditions(Condition.IN);
        rules.setValue("aaa,bb,ccc");
        System.out.println(rules.getSmipleString(rules));
    }

    @Test
    public void  test(){
        String rules = "{\n" +
                "\t\t\"value\":[{\n" +
                "\t\t\t\"filed\": \"column_1\",\n" +
                "\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\"value\": \"aaa\",\n" +
                "\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_2\",\n" +
                "\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\"value\": \"ddd\",\n" +
                "\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_3\",\n" +
                "\t\t\t\"conditions\": \"LESS\",\n" +
                "\t\t\t\"value\": \"eee\",\n" +
                "\t\t\t\"type\": \"DATE_TYPE\",\n" +
                "\t\t\t\"op\": \"OR\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"value\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"filed\": \"column_4\",\n" +
                "\t\t\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\t\t\"value\": \"bbb\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t\t\t},{\n" +
                "\t\t\t\t\t\"filed\": \"column_5\",\n" +
                "\t\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\t\"value\": \"ccc\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\t\"op\": \"AND\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"type\": \"GROUP_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t}],\n" +
                "\t\t\"type\":\"GROUP_TYPE\",\n" +
                "\t\t\"op\": \"AND\"\n" +
                "\t}";
        Rules rules1 = Rules.getRules(rules);
        System.out.println(rules1.toConditions());
    }

    @Test
    public void  test2(){
        String rules = "{\n" +
                "\t\t\"value\":[{\n" +
                "\t\t\t\"filed\": \"column_1\",\n" +
                "\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\"value\": \"aaa\",\n" +
                "\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_2\",\n" +
                "\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\"value\": [{\n" +
                "\t\t\t\t\"filed\": \"column_2\",\n" +
                "\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\"value\": \"ddd\",\n" +
                "\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\"op\": \"AND\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"type\": \"GROUP_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_3\",\n" +
                "\t\t\t\"conditions\": \"LESS\",\n" +
                "\t\t\t\"value\": \"eee\",\n" +
                "\t\t\t\"type\": \"DATE_TYPE\",\n" +
                "\t\t\t\"op\": \"OR\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"value\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"filed\": \"column_4\",\n" +
                "\t\t\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\t\t\"value\": \"bbb\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t\t\t},{\n" +
                "\t\t\t\t\t\"filed\": \"column_5\",\n" +
                "\t\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\t\"value\": \"ccc\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\t\"op\": \"AND\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"type\": \"GROUP_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t}],\n" +
                "\t\t\"type\":\"GROUP_TYPE\",\n" +
                "\t\t\"op\": \"AND\"\n" +
                "\t}";
        Rules rules1 = Rules.getRules(rules);
        rules1.setOp(null);
        System.out.println(rules1.toConditions());
    }
    @Test
    public void  test3(){
        String rules = "{\n" +
                "\t\t\"value\":[{\n" +
                "\t\t\t\"filed\": \"column_1\",\n" +
                "\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\"value\": \"aaa\",\n" +
                "\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_2\",\n" +
                "\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\"value\": [{\n" +
                "\t\t\t\t\"filed\": \"column_2\",\n" +
                "\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\"value\": \"ddd\",\n" +
                "\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\"op\": \"AND\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"filed\": \"column_222\",\n" +
                "\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\"value\": \"222\",\n" +
                "\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\"op\": \"OR\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"type\": \"GROUP_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_3\",\n" +
                "\t\t\t\"conditions\": \"LESS\",\n" +
                "\t\t\t\"value\": \"eee\",\n" +
                "\t\t\t\"type\": \"DATE_TYPE\",\n" +
                "\t\t\t\"op\": \"OR\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"value\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"filed\": \"column_4\",\n" +
                "\t\t\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\t\t\"value\": \"bbb\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t\t\t},{\n" +
                "\t\t\t\t\t\"filed\": \"column_5\",\n" +
                "\t\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\t\"value\": \"ccc\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\t\"op\": \"AND\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"type\": \"GROUP_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t}],\n" +
                "\t\t\"type\":\"GROUP_TYPE\",\n" +
                "\t\t\"op\": \"AND\"\n" +
                "\t}";
        Rules rules1 = Rules.getRules(rules);
        rules1.setOp(null);
        System.out.println(rules1.toConditions());
    } @Test
    public void  test4(){
        String rules = "{\"keyset\":{\n" +
                "\t\t\"value\":[{\n" +
                "\t\t\t\"filed\": \"column_1\",\n" +
                "\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\"value\": \"aaa\",\n" +
                "\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_2\",\n" +
                "\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\"value\": [{\n" +
                "\t\t\t\t\"filed\": \"column_2\",\n" +
                "\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\"value\": \"ddd\",\n" +
                "\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\"op\": \"AND\"\n" +
                "\t\t\t},{\n" +
                "\t\t\t\t\"filed\": \"column_222\",\n" +
                "\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\"value\": \"222\",\n" +
                "\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\"op\": \"OR\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"type\": \"GROUP_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"filed\": \"column_3\",\n" +
                "\t\t\t\"conditions\": \"LESS\",\n" +
                "\t\t\t\"value\": \"eee\",\n" +
                "\t\t\t\"type\": \"DATE_TYPE\",\n" +
                "\t\t\t\"op\": \"OR\"\n" +
                "\t\t},{\n" +
                "\t\t\t\"value\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"filed\": \"column_4\",\n" +
                "\t\t\t\t\t\"conditions\": \"EQ\",\n" +
                "\t\t\t\t\t\"value\": \"bbb\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\"\n" +
                "\t\t\t\t},{\n" +
                "\t\t\t\t\t\"filed\": \"column_5\",\n" +
                "\t\t\t\t\t\"conditions\": \"LIKE\",\n" +
                "\t\t\t\t\t\"value\": \"ccc\",\n" +
                "\t\t\t\t\t\"type\": \"STRING_TYPE\",\n" +
                "\t\t\t\t\t\"op\": \"AND\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"type\": \"GROUP_TYPE\",\n" +
                "\t\t\t\"op\": \"AND\"\n" +
                "\t\t}],\n" +
                "\t\t\"type\":\"GROUP_TYPE\",\n" +
                "\t\t\"op\": \"AND\"\n" +
                "\t}}";
        Map<String, Rules> rulesMap = Rules.getRulesMap(rules);
        for (String next : rulesMap.keySet()){
            Rules rules1 = rulesMap.get(next);
            System.out.println(rules1.toConditions());
        }
    }

    @Test
    public void aa() throws IOException {
        BufferedImage image = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop/微信截图_20201123151743.png"));
        // 高度和宽度
        int height = image.getHeight();
        int width = image.getWidth();

        // 生产背景透明和内容透明的图片
        ImageIcon imageIcon = new ImageIcon(image);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics(); // 获取画笔
        g2D.drawImage(imageIcon.getImage(), 0, 0, null); // 绘制Image的图片，使用了imageIcon.getImage()，目的就是得到image,直接使用image就可以的

        int alpha = 0; // 图片透明度
        // 外层遍历是Y轴的像素
        for (int y = bufferedImage.getMinY(); y < bufferedImage.getHeight(); y++) {
            // 内层遍历是X轴的像素
            for (int x = bufferedImage.getMinX(); x < bufferedImage.getWidth(); x++) {
                int rgb = bufferedImage.getRGB(x, y);
                // 对当前颜色判断是否在指定区间内
                if (colorInRange(rgb)){
                    alpha = 0;
                }else{
                    // 设置为不透明
                    alpha = 255;
                }
                // #AARRGGBB 最前两位为透明度
                rgb = (alpha << 24) | (rgb & 0x00ffffff);
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        // 绘制设置了RGB的新图片,这一步感觉不用也可以只是透明地方的深浅有变化而已，就像蒙了两层的感觉
        g2D.drawImage(bufferedImage, 0, 0, null);

        // 生成图片为PNG
        ImageIO.write(bufferedImage, "png", new File("C:\\Users\\Administrator\\Desktop/lanzhou.png"));
    }


     // 判断是背景还是内容
     public static boolean colorInRange(int color) {
         int red = (color & 0xff0000) >> 16;// 获取color(RGB)中R位
         int green = (color & 0x00ff00) >> 8;// 获取color(RGB)中G位
         int blue = (color & 0x0000ff);// 获取color(RGB)中B位
         // 通过RGB三分量来判断当前颜色是否在指定的颜色区间内
         return red >= color_range && green >= color_range && blue >= color_range;
     }

     //色差范围0~255
     public static int color_range = 210;
}
