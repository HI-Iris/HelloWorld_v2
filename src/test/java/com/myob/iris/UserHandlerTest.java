package com.myob.iris;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserHandlerTest {

    private static List<User> users;
    private static UserRepository userRepository;

    @Before
    public void setup() {
        users = new ArrayList<>();
        users.add(new User("Iris"));

    }


    @Test
    public void givenPostRequestWithoutParameterShouldReturnParameterNotMatch() {
        UserHandler userHandler = new UserHandler(null,null);
        HttpResponse actual = userHandler.getHttpResponse("POST", Optional.empty());
        HttpResponse expected = HttpResult.RESPONSE_PARAMETER_NOT_MATCH;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenPostRequestWithWrongParameterShouldReturnParameterNotMatch() {
        UserHandler userHandler = new UserHandler(null,null);
        Map<String, String> wrongPara = new HashMap<>();
        Optional<Map<String, String>> wrongParam= Optional.of(wrongPara);
        HttpResponse actual = userHandler.getHttpResponse("POST", wrongParam);
        HttpResponse expected = HttpResult.RESPONSE_PARAMETER_NOT_MATCH;
        assertThat(actual, equalTo(expected));
    }






}