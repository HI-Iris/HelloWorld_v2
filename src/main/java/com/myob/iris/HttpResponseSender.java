package com.myob.iris;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class HttpResponseSender implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        HttpResponse httpResponse = getHttpResponse(requestMethod, getParameters(exchange.getRequestURI().getQuery()));
        sendResponse(httpResponse, exchange);
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

    public abstract HttpResponse getHttpResponse(String requestMethod, Optional<Map<String, String>> params);

    private void sendResponse(HttpResponse response, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(response.getStatusCode(), response.getResponse().length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getResponse().getBytes());
        os.close();
    }
}
