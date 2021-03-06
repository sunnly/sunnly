package wang.sunnly.modules.admin.service.impl;

import org.springframework.stereotype.Service;
import wang.sunnly.modules.admin.domain.Resources;
import wang.sunnly.modules.admin.mapper.ResourcesMapper;
import wang.sunnly.modules.admin.service.ResourcesService;
import wang.sunnly.mysql.service.BaseService;
import wang.sunnly.mysql.service.impl.BaseServiceImpl;

import java.util.List;

/**
 * ResourcesServiceImpl
 *
 * @author Sunnly
 * @since 2020/12/22
 */
@Service
public class ResourcesServiceImpl
        extends BaseServiceImpl< ResourcesMapper,  Resources>
        implements  ResourcesService, BaseService< ResourcesMapper,  Resources> {

    @Override
    public List<Resources> getMenuByUsername(String username) {
        return mapper.getMenuByUsername(username);
    }
}
