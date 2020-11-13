package com.rizky.dropwizard.example.auth;

import java.security.Principal;

public class ExampleClaims implements Principal{
    
    String email,role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getName() {
      
        return "Email";
    }
    
    

}
