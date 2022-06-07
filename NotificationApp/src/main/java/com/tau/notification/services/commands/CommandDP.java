package com.tau.notification.services.commands;

public abstract class CommandDP {
    public Object data;
    
    public abstract String execute();

    public void setData(Object data) {
        this.data = data;
    }

}