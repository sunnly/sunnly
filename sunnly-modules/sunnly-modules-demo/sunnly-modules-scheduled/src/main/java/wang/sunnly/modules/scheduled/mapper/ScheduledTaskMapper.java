package wang.sunnly.modules.scheduled.mapper;

import org.springframework.stereotype.Component;
import wang.sunnly.scheduled.entity.ScheduledTaskEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * ScheduledTaskMapper
 *
 * @author Sunnly
 * @since 2020/10/22 0022 21:46
 */
@Component
public class ScheduledTaskMapper {

    private static final int TEST_SIZE = 3;

    public List<ScheduledTaskEntity> taskList() {
        List<ScheduledTaskEntity> scheduledTaskEntityList = new ArrayList<>();
        //从数据库中获取任务列表
        for (int i = 0; i < TEST_SIZE; i++) {
            ScheduledTaskEntity scheduledTaskEntity = new ScheduledTaskEntity();
            scheduledTaskEntity.setTaskKey("task-"+i);
            scheduledTaskEntity.setTaskService("task-"+i);
            scheduledTaskEntity.setInitStartFlag(5);
            scheduledTaskEntity.setStartFlag(true);
            scheduledTaskEntity.setTaskDesc("任务【"+i+"】正在执行");
            scheduledTaskEntity.setTaskCron("0/"+(i+2)+" * * * * ?");

            scheduledTaskEntityList.add(scheduledTaskEntity);
        }
        return scheduledTaskEntityList;
    }

    public ScheduledTaskEntity getByKey(String taskKey){
        //根据任务Key从数据库中获取任务
        ScheduledTaskEntity scheduledTaskEntity = new ScheduledTaskEntity();
        scheduledTaskEntity.setTaskKey(taskKey);
        scheduledTaskEntity.setTaskService(taskKey);
        scheduledTaskEntity.setInitStartFlag(5);
        scheduledTaskEntity.setStartFlag(true);
        scheduledTaskEntity.setTaskDesc("任务【"+taskKey+"】正在执行");
        scheduledTaskEntity.setTaskCron("0/"+(1+2)+" * * * * ?");
        return scheduledTaskEntity;
    }

    public ScheduledTaskEntity getByKey(String taskKey, String beanName){
        //根据任务key和beanName获取任务，这里是模拟，可以根据任务key动态创建任务启动bean
        ScheduledTaskEntity byKey = getByKey(taskKey);
        byKey.setTaskService(beanName);
        return byKey;
    }

}
