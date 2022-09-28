package com.mf.probe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPool {

    @Value("${probe.tasks-thread-pool.core}")
    private int coreSize;

    @Value("${probe.tasks-thread-pool.max}")
    private int max;

    @Value("${probe.tasks-thread-pool.keep-alive-time}")
    private long keepAliveTime;

    @Value("${probe.tasks-thread-pool.queue-size}")
    private int queueSize;

    /**
     * 创建执行任务的线程池
     * @return
     */
    @Bean
    public ThreadPoolExecutor poolExecutor () {
        return new ThreadPoolExecutor(coreSize, max,
                keepAliveTime, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<>(queueSize)
                );
    }
}
