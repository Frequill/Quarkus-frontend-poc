package com.teliacompany.frontend.poc;

import com.teliacompany.frontend.poc.entities.UserEntity;
import com.teliacompany.frontend.poc.proxy.ProxyWebResource;
import com.teliacompany.frontend.poc.entities.LoginEntity;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/main")
public class MainResource {

    @RestClient
    ProxyWebResource proxy;

    @GET
    @Path("/testHello")
    @Produces(MediaType.TEXT_PLAIN)
    public Response testHello(){
        String result = proxy.hello();
        System.out.println("TestHello went off!");
        return Response.ok(result).build();
    }

    /**
     Function now works!

     Uses post to send a "UserEntity" to backend which creates an identical entity and stores it in hashMap
     */
    @POST
    @Path("/mkUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserEntity mkUser(UserEntity userEntity) {
        System.out.println("WE ENTERED mkUser! :)");
        return proxy.mkUser(userEntity);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginEntity loginUser(LoginEntity loginEntity) {
        return proxy.login(loginEntity);
    }

    @GET
    @Path("/getAllUsers")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllUsers(){
        String result = proxy.getAllUsers();
        return Response.ok(result).build();
    }


}