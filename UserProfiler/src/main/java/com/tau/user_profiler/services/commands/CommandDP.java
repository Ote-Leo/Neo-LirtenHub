package com.tau.user_profiler.services.commands;

public abstract class CommandDP {
    public Object data;
    
    public abstract Object execute();

    public void setData(Object data) {
        this.data = data;
    }

}
