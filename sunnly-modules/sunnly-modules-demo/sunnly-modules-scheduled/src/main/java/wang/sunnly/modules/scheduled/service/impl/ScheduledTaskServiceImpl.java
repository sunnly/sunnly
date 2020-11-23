package wang.sunnly.modules.scheduled.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.sunnly.modules.scheduled.mapper.ScheduledTaskMapper;
import wang.sunnly.scheduled.entity.ScheduledTaskEntity;
import wang.sunnly.scheduled.service.BaseScheduledTaskService;

import java.util.List;

/**
 * ScheduledTaskServiceImpl
 *
 * @author Sunnly
 * @since 2020/10/22 0022 21:45
 */
@Service
@Slf4j
public class ScheduledTaskServiceImpl extends BaseScheduledTaskService {

    @Autowired
    private ScheduledTaskMapper scheduledTaskMapper;

    @Override
    public List<ScheduledTaskEntity> taskList() {
        return scheduledTaskMapper.taskList();
    }

    @Override
    public ScheduledTaskEntity getByKey(String taskKey) {
        return scheduledTaskMapper.getByKey(taskKey);
    }

    @Override
    public ScheduledTaskEntity getByKey(String taskKey, String beanName) {
        return scheduledTaskMapper.getByKey(taskKey, beanName);
    }

}
