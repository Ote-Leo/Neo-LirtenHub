package com.tau.authenticator.email;

public interface EmailSender {
    void send(String target, String email);
}
