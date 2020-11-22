package wang.sunnly.modules.asyncfeign.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.common.web.msg.result.ObjectResult;

import java.util.Map;
import java.util.Random;

/**
 * AsyncFeignController
 *
 * @author Sunnly
 * @since 2020/10/30 0030 23:56
 */
@RestController
@RequestMapping
public class AsyncFeignController {

    @RequestMapping("send")
    public ObjectResult<String> send(@RequestParam("send") String send){
        System.out.println(send);
        Random random = new Random();
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ObjectResult<String>().setData("==="+send);
    }
}
