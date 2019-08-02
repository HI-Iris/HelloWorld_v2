package com.myob.iris;

import com.myob.iris.Handler.GreetingHandler;
import com.myob.iris.Handler.UserHandler;
import com.myob.iris.Model.User;
import com.myob.iris.Service.GreetingService;
import com.myob.iris.Dao.UserRepository;
import com.myob.iris.Dao.UserRepositoryImpl;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class HelloWorldApplication {

    public static void main(String[] args) throws IOException {

        final User defaultUser = new User("Iri");
        List<User> users = new ArrayList<>();
        users.add(defaultUser);
        UserRepository userRepository = new UserRepositoryImpl(users);
        GreetingService greetingService = new GreetingService(userRepository);

        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new GreetingHandler(greetingService));
        server.createContext("/users", new UserHandler(userRepository));
        server.setExecutor(null);
        server.start();
        System.out.println("Server started");
    }

}
