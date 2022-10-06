package com.teliacompany.frontend.poc;

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
     Function mkUser does not yet work

     Be flygpar'n förklara hur jag nyttjar flera parametrar... Hela stack overflow ljuger för mig
     */
    @GET
    @Path("/mkUser")
    @Produces({MediaType.TEXT_PLAIN})
    public Response mkUser(@QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("email") String email) {
        String result = proxy.mkUser(username, password, email);
        System.out.println("RESULT = " + result);
        return Response.ok(result).build();
    }

    @GET
    @Path("/getAllUsers")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getAllUsers(){
        String result = proxy.getAllUsers();
        return Response.ok(result).build();
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginEntity loginUser(LoginEntity loginEntity) {
        return proxy.login(loginEntity);
    }


}