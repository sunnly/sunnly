package wang.sunnly.modules.asyncfeign.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.web.msg.result.ObjectResponse;
import wang.sunnly.modules.asyncfeign.server.service.AsyncFeignService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * AsyController
 *
 * @author Sunnly
 * @since 2020/10/30 0030 22:53
 */
@RestController
@RequestMapping("async")
@Slf4j
public class AsyncController {

    @Autowired
    private AsyncFeignService asynFeignService;

    @GetMapping("future")
    public ObjectResponse<List<String>> future() throws ExecutionException, InterruptedException {
        log.info("==========begin {} ", Thread.currentThread().getName());
        long begin = System.currentTimeMillis();
        List<Future<String>> futures = new ArrayList<>();
        futures.add(asynFeignService.send(1));
        futures.add(asynFeignService.send(2));
        futures.add(asynFeignService.send(3));
        futures.add(asynFeignService.send(4));
        Future<List<String>> returnValueManage = asynFeignService.getReturnValueManage(futures);
        ObjectResponse<List<String>> listObjectResult = new ObjectResponse<>(returnValueManage.get());
        log.info("=====done ...{}, {}", Thread.currentThread().getName(), System.currentTimeMillis() - begin);
        return listObjectResult;
    }

    @GetMapping("future1")
    public ObjectResponse<String> future1(){
        List<Future<String>> futures = new ArrayList<>();
        futures.add(asynFeignService.send(1));
        futures.add(asynFeignService.send(2));
        futures.add(asynFeignService.send(3));
        futures.add(asynFeignService.send(4));
        return new ObjectResponse<String>().setData("success");
    }
}
