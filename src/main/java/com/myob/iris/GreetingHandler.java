package com.myob.iris;

import java.time.ZoneId;
import java.time.ZonedDateTime;
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
            message = greetingService.buildGreeting(ZonedDateTime.now(ZoneId.of("Australia/Melbourne")));
            return new HttpResponse(200, message);
        } else {
            return new HttpResponse(501, ErrMsgConstant.REQUEST_NOT_IMPLEMENTED);
        }
    }

}