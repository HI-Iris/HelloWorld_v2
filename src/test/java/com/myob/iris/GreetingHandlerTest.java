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
    private static GreetingBuilder greetingBuilder;

    @Before
    public void setup() {
        users = new ArrayList<>();
        users.add(new User("Iris"));
        greetingBuilder = mock(GreetingBuilder.class);
        greetingHandler = new GreetingHandler(greetingBuilder);
    }

    @Test
    public void givenPostRequestShouldReturnRequestNotImplResponse() {
        HttpResponse actual = greetingHandler.getHttpResponse("POST", Optional.empty());
        HttpResponse expected = BuildinHttpResponse.RESPONSE_REQUEST_NOT_IMPLEMENTED;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenPutRequestShouldReturnRequestNotImplResponse() {
        HttpResponse actual = greetingHandler.getHttpResponse("PUT", Optional.empty());
        HttpResponse expected = BuildinHttpResponse.RESPONSE_REQUEST_NOT_IMPLEMENTED;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenDeleteRequestShouldReturnRequestNotImplResponse() {
        HttpResponse actual = greetingHandler.getHttpResponse("DELETE", Optional.empty());
        HttpResponse expected = BuildinHttpResponse.RESPONSE_REQUEST_NOT_IMPLEMENTED;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenGetRequestShouldCallBuildGreeting() {
        when(greetingBuilder.buildGreeting(any())).thenReturn("Iris");
        HttpResponse actual = greetingHandler.getHttpResponse("GET", Optional.empty());
        HttpResponse expected = new HttpResponse(200, "Iris");
        assertThat(actual, equalTo(expected));
    }
}