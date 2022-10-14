package com.teliacompany.frontend.poc.proxy;

import com.teliacompany.frontend.poc.entities.LoginEntity;
import com.teliacompany.frontend.poc.entities.UserEntity;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.Message;
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
    @Produces(MediaType.TEXT_PLAIN)
    String hello();

    // Can this return through eventbus? If so, this is the new model that we use! - YES it can! //Future Julius
    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    Uni<String> getHello();

    @POST
    @Path("/mkUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Uni<UserEntity> mkUser(UserEntity payloadToSend);

    // FULL URL: http://localhost:8082/myPath/login
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Uni<LoginEntity> login(LoginEntity payloadToSend);

    @GET
    @Path("/logout/{token}")
    // Token sent to backend which logs user out
    String logout(@PathParam("token") String token);

    @GET
    @Path("/getAllUsers")
    Uni<String> getAllUsers();



}