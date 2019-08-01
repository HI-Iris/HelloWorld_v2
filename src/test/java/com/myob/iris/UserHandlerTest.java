package com.myob.iris;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;
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
        userHandler = new UserHandler(userRepository);
        wrongParam = Optional.of(new HashMap<>());
        correctParam = Optional.of(new HashMap<>());
        correctParam.get().put("name", "Bella");
        correctParam.get().put("newName", "Paul");
    }

    @Test
    public void givenPostRequestWithoutParameterShouldReturnParameterNotMatch() {
        HttpResponse actual = userHandler.fulfilRequest("POST", Optional.empty());
        HttpResponse expected = new HttpResponse(404, ErrMsgConstant.PARAMETER_NOT_FOUND);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenPostRequestWithWrongParameterShouldReturnParameterNotMatch() {
        HttpResponse actual = userHandler.fulfilRequest("POST", wrongParam);
        HttpResponse expected = new HttpResponse(404, ErrMsgConstant.PARAMETER_NOT_FOUND);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenPostRequestWithCorrectParameterShouldCallCreateFunction() {
        userHandler.fulfilRequest("POST", correctParam);
        verify(userRepository, times(1)).create(correctParam.get().get("name"));
    }

    @Test
    public void givenGetRequestWithoutParameterShouldCallReadFunction() {
        userHandler.fulfilRequest("GET", Optional.empty());
        verify(userRepository, times(1)).read();
    }

    @Test
    public void givenGetRequestWithWrongParameterShouldCallReadFunction() {
        userHandler.fulfilRequest("GET", wrongParam);
        verify(userRepository, times(1)).read();
    }

    @Test
    public void givenGetRequestWithCorrectParameterShouldCallReadFunction() {
        userHandler.fulfilRequest("GET", correctParam);
        verify(userRepository, times(1)).read();
    }

    @Test
    public void givenPutRequestWithoutParameterShouldReturnParameterNotMatch() {
        HttpResponse actual = userHandler.fulfilRequest("PUT", Optional.empty());
        HttpResponse expected = new HttpResponse(404, ErrMsgConstant.PARAMETER_NOT_FOUND);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenPutRequestWithWrongParameterShouldReturnParameterNotMatch() {
        HttpResponse actual = userHandler.fulfilRequest("PUT", wrongParam);
        HttpResponse expected = new HttpResponse(404, ErrMsgConstant.PARAMETER_NOT_FOUND);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenPutRequestWithCorrectParameterShouldCallCreateFunction() {
        userHandler.fulfilRequest("PUT", correctParam);
        verify(userRepository, times(1)).update(correctParam.get().get("name"), correctParam.get().get("newName"));
    }

    @Test
    public void givenDeleteRequestWithoutParameterShouldReturnParameterNotMatch() {
        HttpResponse actual = userHandler.fulfilRequest("DELETE", Optional.empty());
        HttpResponse expected = new HttpResponse(404, ErrMsgConstant.PARAMETER_NOT_FOUND);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenDeleteRequestWithWrongParameterShouldReturnParameterNotMatch() {
        HttpResponse actual = userHandler.fulfilRequest("DELETE", wrongParam);
        HttpResponse expected = new HttpResponse(404, ErrMsgConstant.PARAMETER_NOT_FOUND);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenDeleteRequestWithCorrectParameterShouldCallDeleteFunction() {
        userHandler.fulfilRequest("DELETE", correctParam);
        verify(userRepository, times(1)).delete(correctParam.get().get("name"));
    }

    @Test
    public void givenPatchRequestWithoutParameterShouldReturnRequestNotImplemented() {
        HttpResponse actual = userHandler.fulfilRequest("PATCH", Optional.empty());
        HttpResponse expected = new HttpResponse(501, ErrMsgConstant.REQUEST_NOT_IMPLEMENTED);
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenPatchRequestWithWrongParameterShouldReturnRequestNotImplemented() {
        HttpResponse actual = userHandler.fulfilRequest("PATCH", wrongParam);
        HttpResponse expected = new HttpResponse(501, ErrMsgConstant.REQUEST_NOT_IMPLEMENTED);
        assertThat(actual, equalTo(expected));
    }
    @Test
    public void givenPatchRequestWithCorrectParameterShouldReturnRequestNotImplemented() {
        HttpResponse actual = userHandler.fulfilRequest("PATCH", correctParam);
        HttpResponse expected = new HttpResponse(501, ErrMsgConstant.REQUEST_NOT_IMPLEMENTED);
        assertThat(actual, equalTo(expected));
    }

}


