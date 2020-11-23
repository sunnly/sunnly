package wang.sunnly.modules.scheduled.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import wang.sunnly.scheduled.job.ScheduledTaskJob;

/**
 * ScheduledTask01
 *
 * @author Sunnly
 * @since 2020/10/22 0022 23:05
 */
@Slf4j
@Component("bean01")
public class ScheduledTask01 implements ScheduledTaskJob {
    @Override
    public void run() {
        log.info("ScheduledTask ==> 01 run 当前线程名称 {}", Thread.currentThread().getName());
    }
}
