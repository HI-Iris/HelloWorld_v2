package com.myob.iris;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class HelloWorldApplication {
    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();
    }

    public static class MyHandler implements HttpHandler {


        @Override
        public void handle(HttpExchange t) throws IOException {
            final User defaultUser = new User("Iris");
            ArrayList<User> users = new ArrayList<>();
            users.add(defaultUser);
            GreetingBuilder greetingBuilder = new GreetingBuilder(users, new Date().toString());
            String response = greetingBuilder.buildGreeting();
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
