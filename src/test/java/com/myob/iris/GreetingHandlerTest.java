package com.myob.iris;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GreetingHandlerTest {
    private static List<User> users;
    private static GreetingHandler greetingHandler;

    @Before
    public void setup() {
        users = new ArrayList<>();
        users.add(new User("Iris"));
        greetingHandler = new GreetingHandler(users,new GreetingBuilder());
    }

    @Test
    public void givenPostRequestShouldReturnRequestNotImplResponse(){
        HttpResponse actual = greetingHandler.getHttpResponse("POST", Optional.empty());
        HttpResponse expected = HttpResult.RESPONSE_REQUEST_NOT_IMPLEMENTED;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenPutRequestShouldReturnRequestNotImplResponse(){
        HttpResponse actual = greetingHandler.getHttpResponse("PUT", Optional.empty());
        HttpResponse expected = HttpResult.RESPONSE_REQUEST_NOT_IMPLEMENTED;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenDeleteRequestShouldReturnRequestNotImplResponse(){
        HttpResponse actual = greetingHandler.getHttpResponse("DELETE", Optional.empty());
        HttpResponse expected = HttpResult.RESPONSE_REQUEST_NOT_IMPLEMENTED;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenGetRequestShouldReturn200StatusCode(){
        HttpResponse actual = greetingHandler.getHttpResponse("GET", Optional.empty());
        int expected = 200;
        assertThat(actual.getStatusCode(), equalTo(expected));
    }
}