package com.sensemore.retry_sam;

import java.io.IOException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@EnableRetry
public class RetryInvoker {

    /* 
    value：指定重试的异常类型，如果不设置，则所有异常都会触发重试。
    maxAttempts：最大重试次数，默认为 3 次。
    backoff：指定重试的延迟策略，包括初始延迟、延迟因子等。 
    */
    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000L, multiplier=1.5))
    public boolean processFile() throws IOException {
        // 如果发生 IOException，则重试最多 5 次
        // 模拟可能会抛出 IOException 的文件处理操作
        throw new RuntimeException("模拟错误");
    }

    @Recover
    public boolean doRecover(Throwable e) {
        System.out.println("全部重试失败, 执行doRecover");
        return false;
    }
}
