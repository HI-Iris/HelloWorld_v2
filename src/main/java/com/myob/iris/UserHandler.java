package com.myob.iris;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserHandler extends Handler implements ParameterGetter{

    private UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        Optional<Map<String, String>> parameters = getParameters(exchange.getRequestURI().getQuery());
        HttpResponse httpResponse = getHttpResponse(requestMethod, parameters);
        super.sendResponse(httpResponse, exchange);
    }

    @Override
    public HttpResponse getHttpResponse(String requestMethod, Optional<Map<String, String>> params) {
        HttpResponse httpResponse;
        switch (requestMethod.toUpperCase()) {
            case "POST":
                if (params.isPresent() && params.get().containsKey("name")) {
                    httpResponse = userRepository.create(params.get().get("name"));
                } else {
                    httpResponse = BuildinHttpResponse.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            case "GET":
                httpResponse = userRepository.read();
                break;
            case "PUT":
                if (params.isPresent() && params.get().containsKey("name") && params.get().containsKey("newName")) {
                    httpResponse = userRepository.update(params.get().get("name"), params.get().get("newName"));
                } else {
                    httpResponse = BuildinHttpResponse.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            case "DELETE":
                if (params.isPresent() && params.get().containsKey("name")) {
                    httpResponse = userRepository.delete( params.get().get("name"));
                } else {
                    httpResponse = BuildinHttpResponse.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            default:
                httpResponse = BuildinHttpResponse.RESPONSE_REQUEST_NOT_IMPLEMENTED;
                break;
        }
        return httpResponse;
    }

    @Override
    public Optional<Map<String, String>> getParameters(String queryString) {
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

