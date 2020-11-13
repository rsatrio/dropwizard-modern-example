package com.rizky.dropwizard.example.auth;

import io.dropwizard.auth.Authorizer;

public class ExampleApiAuthorizer implements Authorizer<ExampleClaims> {

    @Override
    public boolean authorize(ExampleClaims claim, String role) {
        // TODO Auto-generated method stub
        return claim.getRole().equals(role);
    }

}