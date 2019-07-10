package com.myob.iris;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class GreetingHandler extends Handler {
    private GreetingService greetingService;

    public GreetingHandler(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @Override
    public HttpResponse fulfilRequest(String requestMethod, Optional<Map<String, String>> params) {
        String message;
        if (requestMethod.equalsIgnoreCase("GET")) {
            message = greetingService.buildGreeting(new Date());
            return new HttpResponse(200, message);
        } else {
            return new HttpResponse(404, ConstantString.REQUEST_NOT_IMPLEMENTED);
        }
    }

}