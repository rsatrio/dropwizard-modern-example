package com.rizky.dropwizard.example;

//import org.apache.http.client.HttpClient;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.rizky.dropwizard.example.auth.ExampleApiAuthorizer;
import com.rizky.dropwizard.example.auth.ExampleAuthenticatorJWT;
import com.rizky.dropwizard.example.auth.ExampleClaims;
import com.rizky.dropwizard.example.resources.HelloWorldResource;

public class HelloWorldExampleApplication extends Application<HelloWorldExampleConfiguration> {

    public static void main(final String[] args) throws Exception {

        new HelloWorldExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "Hello World";
    }



    @Override
    public void run(final HelloWorldExampleConfiguration configuration,
            final Environment environment) {

        final HelloWorldResource res1=new HelloWorldResource(configuration.getDefaultName());

      //Register Authentication&Authorize
        
        environment.jersey().register(new AuthDynamicFeature(new OAuthCredentialAuthFilter.Builder<ExampleClaims>()
                .setAuthenticator(new ExampleAuthenticatorJWT(configuration))
                .setAuthorizer(new ExampleApiAuthorizer())
//                .setUnauthorizedHandler(new Tnt401ExceptionMapper())
                .setPrefix("Bearer")
                .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(ExampleClaims.class));
        
        
        environment.jersey().register(res1);
        

    }

    @Override
    public void initialize(final Bootstrap<HelloWorldExampleConfiguration> bootstrap) {


        bootstrap.addBundle(new SwaggerBundle<HelloWorldExampleConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(HelloWorldExampleConfiguration configuration) {
                return configuration.getSwagger();
            }
        });
    }

}
