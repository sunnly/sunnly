package wang.sunnly.modules.auth.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import wang.sunnly.modules.auth.domain.ClientServer;
import wang.sunnly.modules.auth.mapper.ClientServerMapper;
import wang.sunnly.modules.auth.mapper.ClientServerMapper;
import wang.sunnly.modules.auth.service.ClientServerService;
import wang.sunnly.modules.auth.service.ClientServerService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

/**
 * ClientServerServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/11
 */
@Service
public class ClientServerServiceImpl
        extends BaseServiceImpl<ClientServerMapper, ClientServer>
        implements ClientServerService, BaseService<ClientServerMapper, ClientServer> {


}
