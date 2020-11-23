package wang.sunnly.modules.scheduled.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.scheduled.entity.ScheduledTaskEntity;
import wang.sunnly.scheduled.job.ScheduledTaskJob;
import wang.sunnly.scheduled.service.ScheduledTaskService;
import wang.sunnly.scheduled.status.ScheduledStatus;

import java.util.List;

/**
 * ScheduledTaskController
 *
 * @author Sunnly
 * @since 2020/10/22 0022 21:41
 */
@RestController
@RequestMapping("/scheduled")
public class ScheduledTaskController {

    @Autowired
    ScheduledTaskService scheduledTaskService;

    /**
     * 获取任务列表
     * @return
     */
    @GetMapping("/taskList")
    public List<ScheduledTaskEntity> taskList() {
        return scheduledTaskService.taskList();
    }

    /**
     * 根据任务Key启动任务
     * @param taskKey 任务Key
     * @return 返回启动状态
     */
    @GetMapping("start/{taskKey}")
    public String start(@PathVariable("taskKey") String taskKey) {
        ScheduledStatus startStatus = scheduledTaskService.start(taskKey);
        switch (startStatus){
            case STARTED:
                return "任务已启动，无需重复启动";
            case INEXISTENCE:
                return "任务不存在";
            default:
                return "任务启动成功";
        }
    }

    @GetMapping("start/{taskKey}/{beanName}")
    public String start(@PathVariable("taskKey") String taskKey, @PathVariable("beanName") String beanName) {
        ScheduledStatus startStatus = scheduledTaskService.start(taskKey, beanName);
        switch (startStatus){
            case STARTED:
                return "任务已启动，无需重复启动";
            case INEXISTENCE:
                return "任务不存在";
            default:
                return "任务启动成功";
        }
    }

    @GetMapping("/stop/{taskKey}")
    public String stop(@PathVariable("taskKey") String taskKey) {
        ScheduledStatus stopStatus = scheduledTaskService.stop(taskKey);
        switch (stopStatus){
            case INEXISTENCE:
                return "任务不存在";
            default:
                return "任务停止成功";
        }
    }

    @GetMapping("/stop")
    public List<String> stopAll() {
        List<String> list = scheduledTaskService.stopAll();
        return list;
    }

    @GetMapping("/restart/{taskKey}")
    public String restart(@PathVariable("taskKey") String taskKey) {
        ScheduledStatus restartStatus = scheduledTaskService.restart(taskKey);
        switch (restartStatus){
            case INEXISTENCE:
                return "任务存在";
            default:
                return "任务重启成功";
        }
    }

}
