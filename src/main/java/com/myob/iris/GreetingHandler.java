package com.myob.iris;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class GreetingHandler extends HttpResponseSender implements HttpHandler {
    private List<User> users;

    public GreetingHandler(List<User> users) {
        this.users = users;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        int statusCode;
        String response;
        HttpResponse httpResponse;
        if (exchange.getRequestMethod().equalsIgnoreCase("GET")) {
            GreetingBuilder greetingBuilder = new GreetingBuilder();
            response = greetingBuilder.buildGreeting(this.users, new Date());
            statusCode = 200;
            httpResponse = new HttpResponse(statusCode, response);
        } else {
            httpResponse = Constant.RESPONSE_REQUEST_NOT_IMPLEMENTED;
        }
        sendResponse(httpResponse, exchange);
    }

}