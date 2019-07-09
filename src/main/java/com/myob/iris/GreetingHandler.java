package com.myob.iris;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.util.Date;

public class GreetingHandler extends Handler {
    private GreetingService greetingService;

    public GreetingHandler(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        HttpResponse httpResponse = fulfilRequest(requestMethod);
        super.sendResponse(httpResponse, exchange);
    }

    private HttpResponse fulfilRequest(String requestMethod) {
        String message;
        if (requestMethod.equalsIgnoreCase("GET")) {
            message = greetingService.buildGreeting(new Date());
            return new HttpResponse(200, message);
        } else {
            return new HttpResponse(404, ConstantString.REQUEST_NOT_IMPLEMENTED);
        }
    }

}