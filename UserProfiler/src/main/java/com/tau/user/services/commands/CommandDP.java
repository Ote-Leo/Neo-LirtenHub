package com.tau.user.services.commands;

import java.security.NoSuchAlgorithmException;

public abstract class CommandDP {
    public Object data;
    
    @Async("asyncExecutor") 
    public abstract Object execute() throws NoSuchAlgorithmException;

    public void setData(Object data) {
        this.data = data;
    }

}
