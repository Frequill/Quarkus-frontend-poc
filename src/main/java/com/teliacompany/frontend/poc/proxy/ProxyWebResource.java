package com.teliacompany.frontend.poc.proxy;

import com.teliacompany.frontend.poc.entities.LoginEntity;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("myPath")
@Produces(MediaType.TEXT_PLAIN)
@RegisterRestClient(baseUri = "http://localhost:8082/")
public interface ProxyWebResource {

    // FULL URL: http://localhost:8082/myPath/hello
    @GET
    @Path("/hello")
    String hello();

    @GET
    @Path("/mkUser")
    String mkUser(@QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("email") String email);

    @GET
    @Path("/getAllUsers")
    String getAllUsers();

    // FULL URL: http://localhost:8082/myPath/login
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    LoginEntity login(LoginEntity payloadToSend);
}
