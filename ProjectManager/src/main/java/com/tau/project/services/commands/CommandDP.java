package com.tau.project.services.commands;

import org.springframework.scheduling.annotation.Async;

public abstract class CommandDP {
    public Object data;
    
    @Async("asyncExecutor") 
    public abstract String execute();

    public void setData(Object data) {
        this.data = data;
    }

}
