package com.myob.iris;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class HelloWorldApplication {

    public static void main(String[] args) throws Exception {

        final User defaultUser = new User("Iris");
        List<User> users = new ArrayList<>();
        users.add(defaultUser);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new GreetingHandler(users));
        server.createContext("/users", new UserHandler(users));
        server.setExecutor(null);
        server.start();
    }

}
