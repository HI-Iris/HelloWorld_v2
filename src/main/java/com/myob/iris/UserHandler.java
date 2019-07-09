package com.myob.iris;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserHandler extends Handler {

    private UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        Optional<Map<String, String>> parameters = getParameters(exchange.getRequestURI().getQuery());
        HttpResponse httpResponse = fulfilRequest(requestMethod, parameters);
        sendResponse(httpResponse, exchange);
    }

    private HttpResponse fulfilRequest(String requestMethod, Optional<Map<String, String>> params) {
        switch (requestMethod.toUpperCase()) {
            case "POST":
                if (params.isPresent() && params.get().containsKey("name")) {
                    return new HttpResponse(200, userRepository.create(params.get().get("name")));
                } else {
                    return new HttpResponse(404, ConstantString.PARAMETER_NOT_FOUND);
                }
            case "GET":
                return new HttpResponse(200, userRepository.read());
            case "PUT":
                if (params.isPresent() && params.get().containsKey("name") && params.get().containsKey("newName")) {
                    return new HttpResponse(200, userRepository.update(params.get().get("name"), params.get().get("newName")));
                } else {
                    return new HttpResponse(404, ConstantString.PARAMETER_NOT_FOUND);
                }
            case "DELETE":
                if (params.isPresent() && params.get().containsKey("name")) {
                    return new HttpResponse(200, userRepository.delete(params.get().get("name")));
                } else {
                    return new HttpResponse(404, ConstantString.PARAMETER_NOT_FOUND);
                }
            default:
                return new HttpResponse(501, ConstantString.REQUEST_NOT_IMPLEMENTED);
        }
    }

    private Optional<Map<String, String>> getParameters(String queryString) {
        if (queryString != null) {
            Map<String, String> result = new HashMap<>();
            for (String param : queryString.split("&")) {
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

