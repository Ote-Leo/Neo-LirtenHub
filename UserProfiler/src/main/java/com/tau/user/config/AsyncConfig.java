package main.java.com.tau.user.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.AsyncConfigurer;

@Component
public class AsyncConfig implements AsyncConfigurer{
    @Bean("asyncExecutor")
    public Executor getAsyncExecutor(){
        return Executors.newCachedThreadPool();
    }
}