package com.tau.authenticator.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String firstName, lastName, email, password;

    /**
     * Adding a default constructor for the 
     * `No Creators, like default construct, exist): cannot deserialize from Object value (no delegate- or property-based Creator`
     * error message
     */
    public RegistrationRequest() {
        this.firstName = null;
        this.lastName  = null;
        this.email     = null;
        this.password  = null;
    }
}