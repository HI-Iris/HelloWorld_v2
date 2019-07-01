package com.myob.iris;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserHandler extends HttpResponseSender implements HttpHandler {

    private List<User> users;
    private UserRepositoryImpl userRepository;

    public UserHandler(List<User> users, UserRepositoryImpl userRepository) {
        this.users = users;
        this.userRepository = userRepository;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Optional<Map<String, String>> params = getParameters(exchange);
        HttpResponse httpResponse;
        switch (exchange.getRequestMethod().toUpperCase()) {
            case "POST":
                if (params.isPresent() && params.get().get("name") != null) {
                    httpResponse = userRepository.create(this.users, params.get().get("name"));
                } else {
                    httpResponse = Constant.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            case "GET":
                httpResponse = userRepository.read(this.users);
                break;
            case "DELETE":
                if (params.isPresent() && params.get().get("name") != null) {
                    httpResponse = userRepository.delete(this.users, params.get().get("name"));
                } else {
                    httpResponse = Constant.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            case "PUT":
                if (params.isPresent() && params.get().get("name") != null && params.get().get("newName") != null) {
                    httpResponse = userRepository.update(this.users, params.get().get("name"), params.get().get("newName"));
                } else {
                    httpResponse = Constant.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            default:
                httpResponse = Constant.RESPONSE_REQUEST_NOT_IMPLEMENTED;

                break;
        }
        sendResponse(httpResponse, exchange);
    }

    private Optional<Map<String, String>> getParameters(HttpExchange exchange) {
        if (exchange.getRequestURI().getQuery() != null) {
            Map<String, String> result = new HashMap<>();
            String query = exchange.getRequestURI().getQuery();
            for (String param : query.split("&")) {
                String[] entry = param.split("=");
                if (entry.length > 1) {
                    result.put(entry[0], entry[1]);
                } else {
                    result.put(entry[0], "");
                }
            }
            return Optional.of(result);
        }
        return Optional.empty();
    }
}

