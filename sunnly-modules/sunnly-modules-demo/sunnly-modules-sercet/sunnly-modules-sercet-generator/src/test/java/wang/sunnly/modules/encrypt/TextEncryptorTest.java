package wang.sunnly.modules.encrypt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TextEncryptorTest
 * 从JKS文件中读取密钥
 *
 * @author Sunnly
 * @since 2020/11/29 0029
 */
@SpringBootTest(classes = SercetGeneratorApplication.class)
@RunWith(SpringRunner.class)
public class TextEncryptorTest {
//    @Autowired
    private TextEncryptor textEncryptor;

    @Test
    public void test() {
        String plainText = "sunnly123456789hhahaha";
        String encryptedText = this.textEncryptor.encrypt(plainText);

        System.out.println();
        System.out.println(encryptedText);
        System.out.println();
        System.out.println();
        String decryptedText = this.textEncryptor.decrypt(encryptedText);
        System.out.println(plainText + "----------" + encryptedText + "-----------" + decryptedText);

    }
}
