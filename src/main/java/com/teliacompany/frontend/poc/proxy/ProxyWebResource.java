package com.teliacompany.frontend.poc.proxy;

import com.teliacompany.frontend.poc.entities.LoginEntity;
import com.teliacompany.frontend.poc.entities.UserEntity;
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

    @POST
    @Path("/mkUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    UserEntity mkUser(UserEntity payloadToSend);

    // FULL URL: http://localhost:8082/myPath/login
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    LoginEntity login(LoginEntity payloadToSend);

    @GET
    @Path("/logout/{token}")
    // Token sent to backend which logs user out
    String logout(@QueryParam("{token}") String token);

    @GET
    @Path("/getAllUsers")
    String getAllUsers();



}