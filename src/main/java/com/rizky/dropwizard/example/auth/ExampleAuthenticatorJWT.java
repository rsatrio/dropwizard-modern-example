package com.rizky.dropwizard.example.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.fusionauth.jwks.JSONWebKeySetHelper;
import io.fusionauth.jwks.domain.JSONWebKey;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.rsa.RSAVerifier;

import java.security.interfaces.RSAPublicKey;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.container.ResourceInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rizky.dropwizard.example.HelloWorldExampleConfiguration;

public class ExampleAuthenticatorJWT  implements Authenticator<String, ExampleClaims>{

    HelloWorldExampleConfiguration config;
   
    public ExampleAuthenticatorJWT(HelloWorldExampleConfiguration config)   {
        this.config=config;   
    }
    
    Logger log1=LoggerFactory.getLogger(ExampleAuthenticatorJWT.class);
    @Override
    public Optional<ExampleClaims> authenticate(String bearer)
            throws AuthenticationException {

        try {
            List<JSONWebKey> keys = JSONWebKeySetHelper.retrieveKeysFromJWKS(config.getJwksUrl());
            
            RSAPublicKey pubKey=(RSAPublicKey)JSONWebKey.parse(keys.get(0));
//            log1.info(new String(pubKey.getEncoded()));
            
            Verifier verify1=RSAVerifier.newVerifier(pubKey);
            
            log1.info("Bearer: "+bearer);
            
            JWT jwt1=JWT.getDecoder().decode(bearer, verify1);

            if(jwt1.expiration.isBefore(ZonedDateTime.now()))   {
                throw new AuthenticationException("Unauthorized");
            }
            ExampleClaims claim1=new ExampleClaims();
           
            claim1.setEmail(jwt1.getString("client_id"));
            claim1.setRole(jwt1.getString("client_id"));
            
          
           
            return Optional.of(claim1);
        }
        catch(Exception e)  {
            log1.error("Error authorization JWT",e);

            throw new AuthenticationException("Unauthorized");      
        }
    }


}
