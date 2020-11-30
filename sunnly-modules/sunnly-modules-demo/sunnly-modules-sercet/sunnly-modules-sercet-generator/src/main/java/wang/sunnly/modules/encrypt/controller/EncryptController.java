package wang.sunnly.modules.encrypt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.web.msg.result.ListResponse;

import javax.annotation.Nullable;

/**
 * EncryptController
 *
 * @author Sunnly
 * @since 2020/11/29 0029
 */
@RestController
@RequestMapping("/")
public class EncryptController {

    @Autowired
    private TextEncryptor textEncryptor;


    @GetMapping("salt/{key}")
    public ListResponse<String> getSalt(@PathVariable("key") String key) {
        String str = "";
        for (int i = 0; i < key.length(); i++) {
            int ch = key.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return new ListResponse<>(str);
    }

    @GetMapping("encrypt/{sercet}")
    public ListResponse<String> enrypt(@PathVariable("sercet") String sercet){
        return new ListResponse<>(textEncryptor.encrypt(sercet));
    }
    @GetMapping("/decrypt/{sercet}")
    public ListResponse<String> decrypt(@PathVariable("sercet") String sercet){
        return new ListResponse<>(textEncryptor.decrypt(sercet));
    }
}
