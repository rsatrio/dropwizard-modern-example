package com.rizky.dropwizard.example.resources;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;
import io.swagger.annotations.OAuth2Definition;
import io.swagger.annotations.Scope;
import io.swagger.annotations.SecurityDefinition;
import io.swagger.annotations.SwaggerDefinition;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;

import com.rizky.dropwizard.example.api.StdResponseV1;


@Api("API for Hello World")
@Path("/v1/hello/")
@Produces(MediaType.APPLICATION_JSON)
@SwaggerDefinition (securityDefinition =
@SecurityDefinition(oAuth2Definitions=
@OAuth2Definition(key="oauth2",tokenUrl="https://demo.identityserver.io/connect/token"
,authorizationUrl="https://demo.identityserver.io/connect/token",
scopes = @Scope(name = "api",description = "api"),
flow=OAuth2Definition.Flow.APPLICATION)))
public class HelloWorldResource {


    private final String defaultName;



    public HelloWorldResource(String defaultName) {

        this.defaultName = defaultName;

    }

    @GET
    @Path("name/{name}")
    @ApiOperation(value = "Hello World", 
    authorizations= @Authorization(scopes=@AuthorizationScope(scope="api", description = "api"), value = "oauth2"),
    notes = "Returns Hello World",response=StdResponseV1.class,httpMethod="GET")
    @ApiResponses(value = {
            @ApiResponse (code = 200, response=StdResponseV1.class,message = "{statusOk:true,message:Sukses,data:[data1:10,etc]}"),
    })
    @RolesAllowed("m2m")
    public StdResponseV1 sayHello(@PathParam("name") Optional<String> name) throws Exception {
        StdResponseV1 hello1=new StdResponseV1();
        try {
            
            final String value = name.orElse(defaultName);

            hello1.setStatusOk(true);
            hello1.setMessage("Hello World to "+value);
          

            return hello1;
        }
        catch(Exception e)  {
            LoggerFactory.getLogger(HelloWorldResource.class).error("Error instantiantion of HelloWorld",e);

            hello1.setStatusOk(false);
            hello1.setMessage("Hello World Failed");

            Response resp=Response.status(404).entity(hello1).build();

            throw new WebApplicationException(resp);

        }



    }
}
