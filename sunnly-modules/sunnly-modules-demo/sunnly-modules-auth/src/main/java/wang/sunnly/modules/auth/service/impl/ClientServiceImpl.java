package wang.sunnly.modules.auth.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import wang.sunnly.modules.auth.domain.Client;
import wang.sunnly.modules.auth.mapper.ClientMapper;
import wang.sunnly.modules.auth.mapper.ClientMapper;
import wang.sunnly.modules.auth.service.ClientService;
import wang.sunnly.modules.auth.service.ClientService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

@Service
public class ClientServiceImpl
        extends BaseServiceImpl<ClientMapper, Client>
        implements ClientService, BaseService<ClientMapper, Client> {

    @Resource
    private ClientMapper clientMapper;

}
