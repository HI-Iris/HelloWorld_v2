package com.myob.iris;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public class GreetingHandler implements HttpHandler {
    private List<User> users;

    public GreetingHandler(List<User> users) {
        this.users = users;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        GreetingBuilder greetingBuilder = new GreetingBuilder();
        String greeting = greetingBuilder.buildGreeting(this.users, new Date());
        t.sendResponseHeaders(200, greeting.length());
        OutputStream os = t.getResponseBody();
        os.write(greeting.getBytes());
        os.close();
    }

}