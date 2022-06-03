package com.tau.authenticator.email;

import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements Predicate<String> {
    private static final String REGEX_EMAIL_VALIDATION = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-zA-Z]{2,})$";

    @Override
    public boolean test(String t) { return t.matches(REGEX_EMAIL_VALIDATION); }
}
