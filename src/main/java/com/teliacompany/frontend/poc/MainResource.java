package com.teliacompany.frontend.poc;

import com.teliacompany.frontend.poc.entities.UserEntity;
import com.teliacompany.frontend.poc.proxy.ProxyWebResource;
import com.teliacompany.frontend.poc.entities.LoginEntity;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("/main")
public class MainResource {

    public HashMap<String, LoginEntity> activeAccounts = new HashMap<>();

    @RestClient
    ProxyWebResource proxy;

    @Inject
    EventBus eventBus; // Should not need an evenBus here... I think?

    @ApplicationScoped
    BackendService backendService;

    @GET
    @Path("/testHello")
    @Produces(MediaType.TEXT_PLAIN)
    public Response testHello(){
        String result = proxy.hello();
        System.out.println("TestHello went off!");
        return Response.ok(result).build();
    }

    /**
     Dummy method designed to test the eventbus and the LoggerService class
     */
    @GET
    @Path("/logBus/{data}")
    @Produces(MediaType.TEXT_PLAIN)
    public String logBus(@QueryParam("data") @DefaultValue("dummyData") String data) {
        String result = String.valueOf(eventBus.publish("myLogger", data));
        return result;
    }

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloBus() {
        Message<String> result = eventBus.requestAndAwait("EB_hello", null);
        System.out.println("Result address: " + result.address());
        System.out.println("Result replyAddress: " + result.replyAddress());

        return result.body();
    }


    /**
     Function now works!

     Uses post to send a "UserEntity" to backend which creates an identical entity and stores it in hashMap

     curl path: curl -H "Content-Type: application/json" -H "Accept: application/json" -X POST -d '{"username":"usernameHere", "password":"passwordHere", "email":"emailHere"}' "http://localhost:8080/main/mkUser"
     */
    @POST
    @Path("/mkUser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserEntity mkUser(UserEntity userEntity) {
        Message<UserEntity> result = eventBus.requestAndAwait("EB_mkUser", userEntity);
        return result.body();
    }

    /**
     Curl path: curl -H "Content-Type: application/json" -H "Accept: application/json" -X POST -d '{"username":"testUser", "password":"password"}' "http://localhost:8080/main/login"
     */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginEntity loginUser(LoginEntity loginEntity) {
        Message<LoginEntity> result = eventBus.requestAndAwait("EB_login", loginEntity);
        return result.body();
    }

    /**
     Curl path: curl http://localhost:8080/main/logout/***YOUR LOGIN TOKEN HERE***


     JAG SVÄR att denna metod fungerade för en timme sedan????  -Be Jonas om hjälp, efter det så är ni redo för eventbus!

     */
    @GET
    @Path("/logout/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout(@PathParam("username") String username) {
        System.out.println("Here is your token = " + activeAccounts.get(username).getLoginToken());

        String result = proxy.logout(activeAccounts.get(username).getLoginToken());
        return Response.ok(result).build();
    }

    @GET
    @Path("/getAllUsers")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllUsers(){
        // Since no data needs to be sent to "BackendService" we simply send no data, the parameter in the called
        // method will be filled with results from backend and then sent back here anyway so it's okay if it's empty when we send it.
        Message<String> result = eventBus.requestAndAwait("EB_getAllUsers", null);
        return result.body();
    }


}