package com.myob.iris;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

public class UserRepositoryImplTest {

    private List<User> users;
    private UserRepositoryImpl userRepository;
    private HttpResponse httpResponse;
    private final User defaultUser = new User("Iris");
    private User bella = new User("Bella");
    private User paul = new User("Paul");

    @Before
    public void setup() {
        httpResponse = new HttpResponse(404, "");
        users = new ArrayList<>();
        users.add(defaultUser);
        users.add(bella);
        users.add(paul);
        userRepository = new UserRepositoryImpl(users);
    }

    @Test
    public void givenNonExistUserNameShouldCreateUserAndReturnSuccessfulResponse() {
        String nameToCreate = "Lee";
        httpResponse = userRepository.create(nameToCreate);
        HttpResponse expected = new HttpResponse(200, "User " + nameToCreate + " created");
        assertThat(httpResponse, equalTo(expected));
        assertThat(users.get(3).getName(), equalTo("Lee"));
    }

    @Test
    public void givenDefaultUserNameShouldNotCreateUserAndReturnDefaultUserResponse() {
        String nameToCreate = "iris";
        httpResponse = userRepository.create(nameToCreate);
        HttpResponse expected = BuildinHttpResponse.RESPONSE_DEFAULT_USER;
        assertThat(httpResponse, equalTo(expected));
    }

    @Test
    public void givenExistUserNameShouldNotCreateUserAndReturnUserExistResponse() {
        String nameToCreate = "bella";
        httpResponse = userRepository.create(nameToCreate);
        HttpResponse expected = BuildinHttpResponse.RESPONSE_USER_EXIST;
        assertThat(httpResponse, equalTo(expected));
    }

    @Test
    public void givenNumericUserNameShouldNotCreateUserAndReturnInvalidUserResponse() {
        String nameToCreate = "bella123";
        httpResponse = userRepository.create(nameToCreate);
        HttpResponse expected = BuildinHttpResponse.RESPONSE_INVALID_USER;
        assertThat(httpResponse, equalTo(expected));
    }

    @Test
    public void callReadFunctionShouldReturnSuccessfulResponse() {
        httpResponse = userRepository.read();
        String userNameList = "Iris, Bella, Paul";
        HttpResponse expected = new HttpResponse(200, userNameList);
        assertThat(httpResponse, equalTo(expected));
    }

    @Test
    public void givenExistUserAndNewNameShouldUpdateAndReturnSuccessfulResponse() {
        String nameToUpdate = users.get(2).getName();
        String newName = "Jane";
        httpResponse = userRepository.update(nameToUpdate, newName);
        HttpResponse expected = new HttpResponse(200, "User " + nameToUpdate + " updated to " + newName);
        assertThat(httpResponse, equalTo(expected));
        assertThat(users.get(2).getName(), equalTo("Jane"));
    }

    @Test
    public void givenDefaultUserShouldNotUpdateAndReturnDefaultUserResponse() {
        String nameToUpdate = defaultUser.getName();
        String newName = "Jane";
        httpResponse = userRepository.update(nameToUpdate, newName);
        HttpResponse expected = BuildinHttpResponse.RESPONSE_DEFAULT_USER;
        assertThat(httpResponse, equalTo(expected));
    }

    @Test
    public void givenExistUserAndExistNameShouldNotUpdateAndReturnUserExistResponse() {
        String nameToUpdate = users.get(2).getName();
        String newName = "bella";
        httpResponse = userRepository.update(nameToUpdate, newName);
        HttpResponse expected = BuildinHttpResponse.RESPONSE_USER_EXIST;
        assertThat(httpResponse, equalTo(expected));
    }

    @Test
    public void givenNotExistUserShouldNotUpdateAndReturnUserNotExistResponse() {
        String nameToUpdate = "Jane";
        String newName = "bella";
        httpResponse = userRepository.update(nameToUpdate, newName);
        HttpResponse expected = BuildinHttpResponse.RESPONSE_USER_NOT_EXIST;
        assertThat(httpResponse, equalTo(expected));
    }

    @Test
    public void givenNumericUserNameShouldNotUpdateAndReturnInvalidUserResponse() {
        String nameToUpdate = "Jane123";
        String newName = "bella";
        httpResponse = userRepository.update(nameToUpdate, newName);
        HttpResponse expected = BuildinHttpResponse.RESPONSE_INVALID_USER;
        assertThat(httpResponse, equalTo(expected));
    }

    @Test
    public void givenNonDefaultUserNameShouldDeleteAndReturnSuccessfulResponse() {
        String nameToDelete = "Bella";
        httpResponse = userRepository.delete(nameToDelete);
        HttpResponse expected = new HttpResponse(200, "User " + nameToDelete + " deleted");
        assertThat(httpResponse, equalTo(expected));
        assertFalse(users.contains(this.bella));
    }

    @Test
    public void givenDefaultUserNameShouldNotDeleteAndReturnDefaultUserResponse() {
        String nameToDelete = "Iris";
        httpResponse = userRepository.delete(nameToDelete);
        HttpResponse expected = BuildinHttpResponse.RESPONSE_DEFAULT_USER;
        assertThat(httpResponse, equalTo(expected));
        assertTrue(users.contains(this.defaultUser));
    }

    @Test
    public void givenNonExistUserNameShouldNotDeleteAndReturnUserNotExistResponse() {
        String nameToDelete = "Jane";
        httpResponse = userRepository.delete(nameToDelete);
        HttpResponse expected = BuildinHttpResponse.RESPONSE_USER_NOT_EXIST;
        assertThat(httpResponse, equalTo(expected));
    }

    @Test
    public void givenNumericUserNameShouldNotDeleteAndReturnInvalidUserResponse() {
        String nameToDelete = "Jane123";
        httpResponse = userRepository.delete(nameToDelete);
        HttpResponse expected = BuildinHttpResponse.RESPONSE_INVALID_USER;
        assertThat(httpResponse, equalTo(expected));
    }
}
