package com.myob.iris;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GreetingHandler extends Handler {
    private List<User> users;
    private GreetingBuilder greetingBuilder;

    public GreetingHandler(List<User> users, GreetingBuilder greetingBuilder) {
        this.users = users;
        this.greetingBuilder = greetingBuilder;
    }

    public HttpResponse getHttpResponse(String requestMethod, Optional<Map<String, String>> params) {
        String response;
        HttpResponse httpResponse;
        if (requestMethod.equalsIgnoreCase("GET")) {
            response = greetingBuilder.buildGreeting(this.users, new Date());
            httpResponse = new HttpResponse(200, response);
        } else {
            httpResponse = BuildinHttpResponse.RESPONSE_REQUEST_NOT_IMPLEMENTED;
        }
        return httpResponse;
    }

}