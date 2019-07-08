package com.myob.iris;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class GreetingHandler extends Handler {
    private GreetingService greetingService;

    public GreetingHandler(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        HttpResponse httpResponse = getHttpResponse(requestMethod, Optional.empty());
        super.sendResponse(httpResponse, exchange);
    }

    @Override
    public HttpResponse getHttpResponse(String requestMethod, Optional<Map<String, String>> parameters) {
        String response;
        HttpResponse httpResponse;
        if (requestMethod.equalsIgnoreCase("GET")) {
            response = greetingService.buildGreeting(new Date());
            httpResponse = new HttpResponse(200, response);
        } else {
            httpResponse = BuildinHttpResponse.RESPONSE_REQUEST_NOT_IMPLEMENTED;
        }
        return httpResponse;
    }

}