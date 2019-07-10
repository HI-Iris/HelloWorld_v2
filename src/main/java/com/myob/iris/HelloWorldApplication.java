package com.myob.iris;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class HelloWorldApplication {

    public static void main(String[] args) throws IOException {

        final User defaultUser = new User("Iris");
        List<User> users = new ArrayList<>();
        users.add(defaultUser);
        UserRepository userRepository = new UserRepositoryImpl(users);
        GreetingService greetingService = new GreetingService(userRepository);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new GreetingHandler(greetingService));
        server.createContext("/users", new UserHandler(userRepository));
        server.setExecutor(null);
        server.start();
    }

}
