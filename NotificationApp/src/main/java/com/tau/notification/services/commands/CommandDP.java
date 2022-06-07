package com.tau.notification.services.commands;

public abstract class CommandDP {
    public Object data;

    @Async("asyncExecutor")
    public abstract String execute();

    public void setData(Object data) {
        this.data = data;
    }

}