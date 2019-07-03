package com.myob.iris;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GreetingHandler extends HttpResponseSender {
    private List<User> users;

    public GreetingHandler(List<User> users) {
        this.users = users;
    }

    public HttpResponse getHttpResponse(String requestMethod, Optional<Map<String, String>> params) {
        String response;
        int statusCode;
        HttpResponse httpResponse;
        if (requestMethod.equalsIgnoreCase("GET")) {
            GreetingBuilder greetingBuilder = new GreetingBuilder();
            response = greetingBuilder.buildGreeting(this.users, new Date());
            statusCode = 200;
            httpResponse = new HttpResponse(statusCode, response);
        } else {
            httpResponse = HttpResult.RESPONSE_REQUEST_NOT_IMPLEMENTED;
        }
        return httpResponse;
    }

}