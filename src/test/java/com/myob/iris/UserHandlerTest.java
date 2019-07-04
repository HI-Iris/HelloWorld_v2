package com.myob.iris;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;


public class UserHandlerTest {

    private static List<User> users;
    private static UserHandler userHandler;
    private static Optional<Map<String, String>> wrongParam;
    private static Optional<Map<String, String>> correctParam;
    private static UserRepository userRepository;

    @Before
    public void setup() {
        userRepository = mock(UserRepositoryImpl.class);
        users = new ArrayList<>();
        users.add(new User("Iris"));
        userHandler = new UserHandler(users, userRepository);
        wrongParam = Optional.of(new HashMap<>());
        correctParam = Optional.of(new HashMap<>());
        correctParam.get().put("name", "Bella");
        correctParam.get().put("newName", "Paul");
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
    public void givenDeleteRequestWithoutParameterShouldReturnParameterNotMatch() {
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
    public void givenPutRequestWithoutParameterShouldReturnParameterNotMatch() {
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

    @Test
    public void givenPostRequestWithCorrectParameterShouldCallCreateFunction() {
        userHandler.getHttpResponse("POST", correctParam);
        verify(userRepository, times(1)).create(users, correctParam.get().get("name"));
    }

    @Test
    public void givenGetRequestShouldCallReadFunction() {
        userHandler.getHttpResponse("GET", correctParam);
        verify(userRepository, times(1)).read(users);
    }

    @Test
    public void givenPutRequestWithCorrectParameterShouldCallCreateFunction() {
        userHandler.getHttpResponse("PUT", correctParam);
        verify(userRepository, times(1)).update(users, correctParam.get().get("name"), correctParam.get().get("newName"));
    }

    @Test
    public void givenDeleteRequestWithCorrectParameterShouldCallCreateFunction() {
        userHandler.getHttpResponse("DELETE", correctParam);
        verify(userRepository, times(1)).delete(users, correctParam.get().get("name"));
    }

}


