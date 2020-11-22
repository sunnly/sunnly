package wang.sunnly.modules.asyncfeign.server.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import wang.sunnly.common.web.msg.result.ObjectResult;
import wang.sunnly.modules.asyncfeign.server.feign.AsyncFeign;
import wang.sunnly.modules.asyncfeign.server.service.AsyncFeignService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * AsynFeignServiceImpl
 *
 * @author Sunnly
 * @since 2020/10/30 0030 22:58
 */
@Service
@Slf4j
public class AsynFeignServiceImpl implements AsyncFeignService {

    @Autowired
    private AsyncFeign asyncFeign;

    @Override
    @Async("taskPoolExecutor")
    public Future<String> send(int i) {
        log.info("feign 异步执行 start ...{} ", Thread.currentThread().getName());
        long begin = System.currentTimeMillis();
        ObjectResult<String> send = asyncFeign.send("sss" + i);
        log.info("feign 异步执行 done ...{}, {}", Thread.currentThread().getName(), System.currentTimeMillis() - begin);
        return new AsyncResult<>(send.getData());
    }


    @Async("taskPoolExecutor")
    @Override
    public Future<List<String>> getReturnValueManage(List<Future<String>> futures) throws ExecutionException, InterruptedException {

        log.info("feign 异步执行start..getReturnValueMange.{}", Thread.currentThread().getName());
        long currentTime = System.currentTimeMillis();
        List<String> sends = new ArrayList<>();
        System.out.println();
        for (Future<String> future : futures) {
            sends.add(future.get());
        }
        log.info("" + sends.size());
        log.info("feign 异步执行done..getReturnValueMange.{}, {}", Thread.currentThread().getName(), String.valueOf(System.currentTimeMillis() - currentTime));
        return new AsyncResult<>(sends);

    }
}
