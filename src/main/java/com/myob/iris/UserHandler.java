package com.myob.iris;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserHandler extends HttpResponseSender implements HttpHandler {

    private List<User> users;
    private UserRepositoryImpl userRepository;

    public UserHandler(List<User> users, UserRepositoryImpl userRepository) {
        this.users = users;
        this.userRepository = userRepository;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, String> params = getParameters(exchange.getRequestURI().getQuery());
        HttpResponse myResponse = Constant.RESP_NOT_IMPLEMENTE;
        switch (exchange.getRequestMethod().toUpperCase()) {
            case "POST":
                myResponse = userRepository.create(this.users, params.get("name"));
                break;
            case "GET":
                myResponse = userRepository.read(this.users);
                break;
            case "DELETE":
                myResponse = userRepository.delete(this.users, params.get("name"));
                break;
            case "PUT":
                myResponse = userRepository.update(this.users, params.get("name"), params.get("newName"));
                break;
            default:
                break;
        }
        sendResponse(myResponse, exchange);
    }

    private Map<String, String> getParameters(String query) {
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }

}
