package com.teliacompany.frontend.poc;

import com.teliacompany.frontend.poc.entities.LoginEntity;
import com.teliacompany.frontend.poc.entities.UserEntity;
import com.teliacompany.frontend.poc.proxy.ProxyWebResource;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.Message;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventbusListener {

    @RestClient
    ProxyWebResource proxy;

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
    public void EB_login(Message<LoginEntity> data) {
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

    @ConsumeEvent("EB_mkUser")
    public void EB_mkUser(Message<UserEntity> data) {
        Uni<UserEntity> result = proxy.mkUser(data.body());

        result.subscribe().with(success -> {
            System.out.println("Got data from backend!");
            data.reply(success);
        },
        failure -> {
            System.out.println("Failed to call backend, failure = " + failure);
            data.reply("Failed to send call to backend");
        });
    }

    @ConsumeEvent("EB_getAllUsers")
    public void EB_getAllUsers(Message<String> noData) {
        Uni<String> result = proxy.getAllUsers();

        result.subscribe().with(success -> {
            System.out.println("Got response from the backend!");
            noData.reply(success);
        },
        failure -> {
            System.out.println("Failed to make REST service call, failure = " + failure);
            noData.reply("Failed to send backend call...");
        });
    }















}