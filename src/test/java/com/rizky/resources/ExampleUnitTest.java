package com.rizky.resources;

import static org.junit.Assert.assertThat;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.testing.junit.ResourceTestRule;

import java.util.Optional;

import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rizky.dropwizard.example.HelloWorldExampleConfiguration;
import com.rizky.dropwizard.example.api.StdResponseV1;
import com.rizky.dropwizard.example.auth.ExampleApiAuthorizer;
import com.rizky.dropwizard.example.auth.ExampleAuthenticatorJWT;
import com.rizky.dropwizard.example.auth.ExampleClaims;
import com.rizky.dropwizard.example.resources.HelloWorldResource;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExampleUnitTest {

    static HelloWorldExampleConfiguration config1=new HelloWorldExampleConfiguration();
    static HelloWorldResource helloResource=new HelloWorldResource(config1.getDefaultName());
    static ExampleAuthenticatorJWT auth1=Mockito.mock(ExampleAuthenticatorJWT.class);
    static ExampleApiAuthorizer authorize1=Mockito.mock(ExampleApiAuthorizer.class);

    static final Logger log1=LoggerFactory.getLogger(ExampleUnitTest.class);

    @ClassRule
    public static ResourceTestRule RULE=ResourceTestRule.builder()
    .addProvider(new AuthDynamicFeature(new OAuthCredentialAuthFilter.Builder<ExampleClaims>()
            .setAuthenticator(auth1)
            .setAuthorizer(authorize1)
            .setPrefix("Bearer")
            .buildAuthFilter()))
            .addProvider(RolesAllowedDynamicFeature.class)
            .addProvider(new AuthValueFactoryProvider.Binder<>(ExampleClaims.class))
            .addResource(helloResource).build();

    @BeforeClass
    public static void prepareDataForTest() throws Exception    {

    }


    @Test
    public void B2bTest1()  throws Exception{


        ExampleClaims claim=new ExampleClaims();
        claim.setEmail("test@test.com");
        claim.setRole("m2m");
        Optional<ExampleClaims> claim1=Optional.of(claim);

        //Mock authentication
        Mockito.when(auth1.authenticate(Mockito.any())).thenReturn(claim1);
        Mockito.when(authorize1.authorize(Mockito.any(), Mockito.any())).thenReturn(true);
        Mockito.when(authorize1.authorize(Mockito.any(), Mockito.any(),Mockito.any())).thenReturn(true);

        StdResponseV1 respDecoded=RULE.target("/v1/hello/name/Joe")
                .request()
                .header("Authorization", "Bearer testing")
                .get()
                .readEntity(StdResponseV1.class);


        assertThat(respDecoded.isStatusOk(), CoreMatchers.is(true));
        assertThat(respDecoded.getMessage().contains("Joe"), CoreMatchers.is(true));


        Mockito.when(auth1.authenticate(Mockito.any())).thenReturn(null);
        Response resp=RULE.target("/v1/hello/name/Joe")
                .request()
                .header("Authorization", "Bearer testing")
                .get();

        assertThat(resp.getStatus(), CoreMatchers.is(500));

    }


}
