package wang.sunnly.modules.logger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import wang.sunnly.modules.logger.annotation.IgnoreMacroLogger;
import wang.sunnly.modules.logger.annotation.MacroLogger;
import wang.sunnly.modules.logger.entity.LoggerEntity;

/**
 * LoggerController
 *
 * @author Sunnly
 * @since 2020/10/26 0026 20:58
 */
@RestController
public class LoggerController {

    @GetMapping(value = "test1",name = "test:list")
    @MacroLogger(name = "test1查询", desc = "这里可以记录日志信息1111")
    public String test1(){
        return "success";
    }


    @PostMapping(value = "test11",name = "test:list")
    @MacroLogger(name = "test11查询", desc = "这里可以记录日志信息111111111")
    public String test11(){
        return "success";
    }

    @GetMapping(value = "test2", name = "test:list2")
    @IgnoreMacroLogger
    public String test2(@RequestBody String name){
        return "success"+name;
    }

    @GetMapping(value = "test3", name = "test:list3")
    public String test3(@RequestBody LoggerEntity loggerEntity){
        return "success"+loggerEntity;
    }
}
