package wang.sunnly.modules.scheduled.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.schedule.entity.ScheduledTaskEntity;
import wang.sunnly.schedule.service.ScheduledTaskService;

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

    @GetMapping("/taskList")
    public List<ScheduledTaskEntity> taskList() {
        return scheduledTaskService.taskList();
    }

    @GetMapping("start/{taskKey}")
    public String start(@PathVariable("taskKey") String taskKey) {
        return scheduledTaskService.start(taskKey) ? "任务启动成功" : "任务启动失败";
    }

    @GetMapping("start/{taskKey}/{beanName}")
    public String start(@PathVariable("taskKey") String taskKey, @PathVariable("beanName") String beanName) {
        return scheduledTaskService.start(taskKey, beanName) ? "任务启动成功" : "任务启动失败";
    }

    @GetMapping("/stop/{taskKey}")
    public String stop(@PathVariable("taskKey") String taskKey) {
        return scheduledTaskService.stop(taskKey) ? "任务停止成功" : "任务停止失败";
    }

    @GetMapping("/restart/{taskKey}")
    public String restart(@PathVariable("taskKey") String taskKey) {
        return scheduledTaskService.restart(taskKey) ? "任务重启成功" : "任务重启失败";
    }

}
