package com.rizky.dropwizard.example;

import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.*;

import javax.validation.constraints.*;

public class HelloWorldExampleConfiguration extends Configuration {


    private String JwksUrl= "https://demo.identityserver.io/.well-known/openid-configuration/jwks";
    private String defaultName="it's me";


    private final SwaggerBundleConfiguration swagger = new SwaggerBundleConfiguration();


    @JsonProperty("JwksUrl")
    public String getJwksUrl() {
        return JwksUrl;
    }



    
    public void setJwksUrl(String jwksUrl) {
        JwksUrl = jwksUrl;
    }



    @JsonProperty("swagger")
    public SwaggerBundleConfiguration getSwagger() {
        return swagger;
    }



    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }




    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }


    
}
