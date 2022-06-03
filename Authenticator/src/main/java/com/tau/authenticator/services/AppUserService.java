package com.tau.authenticator.services;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tau.authenticator.models.AppUser;
import com.tau.authenticator.models.ConfirmationToken;
import com.tau.authenticator.repositories.AppUserRepository;
import com.tau.authenticator.sercurity.PasswordEncoder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {
    private static final String USER_NOT_FOUND_MESSAGE = "CANNOT FIND ANY USER WITH EMAIL: %s";
    private static final String USER_ALREADY_EXISTS_MESSAGE = "A USER WITH EMAIL %s ALREADY EXISTS!";
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
    }

    public String signUpUser(AppUser appUser) {
        Boolean userExists = appUserRepository.findUserByEmail(appUser.getEmail()).isPresent();
        if(userExists) {
            // TODO: CHECK IF ATTRIBUTES ARE THE SAME AND
            // TODO: IF EMAIL NOT CONFIRMED SEND COMFIRMATION EMAIL

            throw new IllegalStateException(String.format(USER_ALREADY_EXISTS_MESSAGE, appUser.getEmail()));
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(30), appUser);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO: SEND AN EMAIL TELLING THE USER THAT HE/SHE HAS BEEN CONFIRMED

        return token;
    }


    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }
}
