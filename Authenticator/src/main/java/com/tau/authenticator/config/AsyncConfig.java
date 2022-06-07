package com.tau.authenticator.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;

public class AsyncConfig implements AsyncConfigurer{
    @Bean("asyncExecutor")
    public Executor getAsyncExecutor(){
        return Executors.newCachedThreadPool();
    }
} 