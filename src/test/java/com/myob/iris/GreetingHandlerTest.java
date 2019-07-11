package com.myob.iris;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class GreetingHandlerTest {
    private static List<User> users;
    private static GreetingHandler greetingHandler;
    private static GreetingService greetingService;

    @Before
    public void setup() {
        users = new ArrayList<>();
        users.add(new User("Iris"));
        greetingService = mock(GreetingService.class);
        greetingHandler = new GreetingHandler(greetingService);
    }

    @Test
    public void givenPostRequestShouldReturnRequestNotImplemented() {
        HttpResponse actual = greetingHandler.fulfilRequest("POST", Optional.empty());
        HttpResponse expected =  new HttpResponse(501, ConstantString.REQUEST_NOT_IMPLEMENTED);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenPutRequestShouldReturnRequestNotImplemented() {
        HttpResponse actual = greetingHandler.fulfilRequest("PUT", Optional.empty());
        HttpResponse expected =  new HttpResponse(501, ConstantString.REQUEST_NOT_IMPLEMENTED);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenDeleteRequestShouldReturnRequestNotImplemented() {
        HttpResponse actual = greetingHandler.fulfilRequest("DELETE", Optional.empty());
        HttpResponse expected =  new HttpResponse(501, ConstantString.REQUEST_NOT_IMPLEMENTED);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenGetRequestShouldCallBuildGreeting() {
        when(greetingService.buildGreeting(any())).thenReturn("Iris");
        HttpResponse actual = greetingHandler.fulfilRequest("GET", Optional.empty());
        HttpResponse expected = new HttpResponse(200, "Iris");
        assertThat(actual, equalTo(expected));
    }
}