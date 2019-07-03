package com.myob.iris;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserHandlerTest {

    private static List<User> users;
    private static UserHandler userHandler;
    private static Optional<Map<String, String>> wrongParam;

    @Before
    public void setup() {
        userHandler = new UserHandler(null,null);
        wrongParam= Optional.of(new HashMap<>());
        users = new ArrayList<>();
        users.add(new User("Iris"));
    }

    @Test
    public void givenPostRequestWithoutParameterShouldReturnParameterNotMatch() {
        HttpResponse actual = userHandler.getHttpResponse("POST", Optional.empty());
        HttpResponse expected = HttpResult.RESPONSE_PARAMETER_NOT_MATCH;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenPostRequestWithWrongParameterShouldReturnParameterNotMatch() {
        HttpResponse actual = userHandler.getHttpResponse("POST", wrongParam);
        HttpResponse expected = HttpResult.RESPONSE_PARAMETER_NOT_MATCH;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenDeleteRequestWithoutParameterShouldReturnParameterNotMatch(){
        HttpResponse actual = userHandler.getHttpResponse("DELETE", Optional.empty());
        HttpResponse expected = HttpResult.RESPONSE_PARAMETER_NOT_MATCH;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenDeleteRequestWithWrongParameterShouldReturnParameterNotMatch() {
        HttpResponse actual = userHandler.getHttpResponse("DELETE", wrongParam);
        HttpResponse expected = HttpResult.RESPONSE_PARAMETER_NOT_MATCH;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenPutRequestWithoutParameterShouldReturnParameterNotMatch(){
        HttpResponse actual = userHandler.getHttpResponse("PUT", Optional.empty());
        HttpResponse expected = HttpResult.RESPONSE_PARAMETER_NOT_MATCH;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenPutRequestWithWrongParameterShouldReturnParameterNotMatch() {
        HttpResponse actual = userHandler.getHttpResponse("PUT", wrongParam);
        HttpResponse expected = HttpResult.RESPONSE_PARAMETER_NOT_MATCH;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenPatchRequestWithoutParameterShouldReturnParameterNotMatch() {
        HttpResponse actual = userHandler.getHttpResponse("PATCH", Optional.empty());
        HttpResponse expected = HttpResult.RESPONSE_REQUEST_NOT_IMPLEMENTED;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenPatchRequestWithWrongParameterShouldReturnParameterNotMatch() {
        HttpResponse actual = userHandler.getHttpResponse("PATCH", wrongParam);
        HttpResponse expected = HttpResult.RESPONSE_REQUEST_NOT_IMPLEMENTED;
        assertThat(actual, equalTo(expected));
    }

}