package wang.sunnly.modules.security.client.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.modules.security.client.service.ClientService1;

/**
 * ClientServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/30
 */
@Service
public class ClientService1Impl implements ClientService1 {
    @Override
    public String aaaaa(String a) {
        return a+"ddddddddddddd";
    }
}
