package wang.sunnly.modules.asyncfeign.server.service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * AsynFeignService
 *
 * @author Sunnly
 * @since 2020/10/30 0030 22:58
 */
public interface AsyncFeignService {

    /**
     * 发送
     * @param i i
     * @return Future
     */
    Future<String> send(int i);

    /**
     * 获取返回值
     * @param futures futures列表
     * @return 返回值
     * @throws ExecutionException ExecutionException
     * @throws InterruptedException InterruptedException
     */
    Future<List<String>> getReturnValueManage(List<Future<String>> futures) throws ExecutionException, InterruptedException;
}