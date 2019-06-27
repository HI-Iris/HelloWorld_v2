package com.myob.iris;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class UserHandler implements HttpHandler {

    private List<User> users;

    public UserHandler(List<User> users) {
        this.users=users;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String content;
        switch (exchange.getRequestMethod().toUpperCase()) {
            case "POST":
                users.add(new User("Bella"));
                content = "post";
                break;
            case "GET":
                content = "get";
                break;
            case "DELETE":
                content = "delete";
                break;
            case "PUT":
                content = "put";
                break;
            default:
                content = "this is a sample";
        }
        returnResponse(content, exchange);
    }

    public void returnResponse(String content, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(200, exchange.getResponseHeaders().size());
        OutputStream os = exchange.getResponseBody();
        os.write(content.getBytes());
        os.close();
    }


}
