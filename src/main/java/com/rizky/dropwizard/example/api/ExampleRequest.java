package com.rizky.dropwizard.example.api;

import com.rizky.dropwizard.example.validation.CheckStringContent;

public class ExampleRequest {
    
    @CheckStringContent(message="must contain John")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
