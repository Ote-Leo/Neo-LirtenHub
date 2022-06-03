package com.tau.authenticator.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.tau.authenticator.email.EmailSender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender javaMailSender;

    @Override
    @Async
    public void send(String target, String email) {
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            mimeMessageHelper.setText(email, true);
            mimeMessageHelper.setTo(target);
            mimeMessageHelper.setSubject("LirtenHub Registration Confirmation Email");
            mimeMessageHelper.setFrom("lirten@tau.com");
            javaMailSender.send(mimeMessage);

        } catch(MessagingException e) {

            LOGGER.error("FAIL TO SEND EMAIL", e);
            throw new IllegalStateException();

        }
    }
}
