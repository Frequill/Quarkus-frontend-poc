package com.teliacompany.frontend.poc;

import com.teliacompany.frontend.poc.entities.LoginEntity;
import com.teliacompany.frontend.poc.proxy.ProxyWebResource;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BackendService {

    @RestClient
    ProxyWebResource proxy;

    // When I put this method THIS way I get error: "(NO_HANDLERS,-1) No handlers for address EB_backend".  -- Why? :/

    // Until issue with blocked calls is resolved, we fake our backend calls

    @ConsumeEvent("EB_hello")
    public void EB_hello(Message<String> data) {
        Uni<String> result = proxy.getHello();

        result.subscribe().with(success -> {
            System.out.println("Got response from backend!");
            data.reply(success);
        },
        failure -> {
            System.out.println("Call to backend REST service failed, failure = " + failure);
            data.reply("Failed sending call to backend");
        });
    }

    @ConsumeEvent("EB_login")
    public void EB_login(Message<LoginEntity> data){
        Uni<LoginEntity> result = proxy.login(data.body());

        result.subscribe().with(success -> {
            System.out.println("Got data from backend!");
            data.reply(success);
        },
        failure -> {
            System.out.println("Call to backend REST service failed, failure = " + failure);
            data.reply("Failed sending call to backend");
        });
    }

    @ConsumeEvent("EB_helloCallx")
    public String EB_helloCallx(String data) {
        System.out.println("Received data: " + data);
        // String result = proxy.hello();
        String result = "fakedResult!";

        System.out.println("Result = " + result);
        return result;
    }


}