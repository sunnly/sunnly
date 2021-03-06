package wang.sunnly.modules.admin.service;

import wang.sunnly.modules.admin.domain.Resources;
import wang.sunnly.modules.admin.mapper.ResourcesMapper;
import wang.sunnly.mysql.service.BaseService;

import java.util.List;

/**
 * ResourcesService
 *
 * @author Sunnly
 * @since 2020/12/22
 */
public interface ResourcesService extends BaseService< ResourcesMapper,  Resources> {

    List<Resources> getMenuByUsername(String username);
}
