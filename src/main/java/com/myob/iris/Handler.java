package com.myob.iris;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public abstract class Handler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        Optional<Map<String, String>> parameters = getParameters(exchange.getRequestURI().getQuery());
        HttpResponse httpResponse = fulfilRequest(requestMethod, parameters);
        sendResponse(httpResponse, exchange);
    }

    public abstract HttpResponse fulfilRequest(String requestMethod, Optional<Map<String, String>> params);

    private void sendResponse(HttpResponse response, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(response.getStatusCode(), response.getResponse().length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getResponse().getBytes());
        os.close();
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
