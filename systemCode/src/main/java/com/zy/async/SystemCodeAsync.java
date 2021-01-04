package com.zy.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Component
public class SystemCodeAsync {

    @Async
    public Future<String> AsyncMethod() throws InterruptedException {
        Thread.sleep(3000);
        String flag = "异步方法调用";
        return new AsyncResult<>(flag);

    }
}
