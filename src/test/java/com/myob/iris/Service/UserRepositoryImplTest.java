package com.myob.iris.Service;

import com.myob.iris.Model.ErrMsgConstant;
import com.myob.iris.Model.User;
import com.myob.iris.Service.GreetingService;
import com.myob.iris.Service.UserRepositoryImpl;
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
    private final User defaultUser = new User("Iris");
    private User bella = new User("Bella");
    private User paul = new User("Paul");

    @Before
    public void setup() {
        users = new ArrayList<>();
        users.add(defaultUser);
        users.add(bella);
        users.add(paul);
        userRepository = new UserRepositoryImpl(users);
    }

    @Test
    public void givenNonExistUserNameShouldCreateUserAndReturnSuccessfulResponse() {
        String nameToCreate = "Lee";
        String actual = userRepository.create(nameToCreate);
        String expected = "User " + nameToCreate + " created";
        assertThat(actual, equalTo(expected));
        assertThat(users.get(3).getName(), equalTo("Lee"));
    }

    @Test
    public void givenDefaultUserNameShouldNotCreateUserAndReturnDefaultUserResponse() {
        String nameToCreate = "iris";
        String actual  = userRepository.create(nameToCreate);
        String expected = ErrMsgConstant.DEFAULT_USER;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenExistUserNameShouldNotCreateUserAndReturnUserExistResponse() {
        String nameToCreate = "bella";
        String actual  = userRepository.create(nameToCreate);
        String expected = ErrMsgConstant.USER_EXIST;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenNumericUserNameShouldNotCreateUserAndReturnInvalidUserResponse() {
        String nameToCreate = "bella123";
        String actual = userRepository.create(nameToCreate);
        String expected = ErrMsgConstant.INVALID_INPUT;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void callReadFunctionShouldReturnSuccessfulResponse() {
        String actual = userRepository.read();
        String expected = "Iris, Bella, Paul";
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenExistUserAndNewNameShouldUpdateAndReturnSuccessfulResponse() {
        String nameToUpdate = users.get(2).getName();
        String newName = "Jane";
        String actual  = userRepository.update(nameToUpdate, newName);
        String expected = "User " + nameToUpdate + " updated to " + newName;
        assertThat(actual, equalTo(expected));
        assertThat(users.get(2).getName(), equalTo("Jane"));
    }

    @Test
    public void givenDefaultUserShouldNotUpdateAndReturnDefaultUserResponse() {
        String nameToUpdate = defaultUser.getName();
        String newName = "Jane";
        String actual  = userRepository.update(nameToUpdate, newName);
        String expected = ErrMsgConstant.DEFAULT_USER;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenExistUserAndExistNameShouldNotUpdateAndReturnUserExistResponse() {
        String nameToUpdate = users.get(2).getName();
        String newName = "bella";
        String actual = userRepository.update(nameToUpdate, newName);
        String expected = ErrMsgConstant.USER_EXIST;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenNotExistUserShouldNotUpdateAndReturnUserNotExistResponse() {
        String nameToUpdate = "Jane";
        String newName = "bella";
        String actual = userRepository.update(nameToUpdate, newName);
        String expected = ErrMsgConstant.USER_NOT_EXIST;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenNumericUserNameShouldNotUpdateAndReturnInvalidUserResponse() {
        String nameToUpdate = "Jane123";
        String newName = "bella";
        String actual = userRepository.update(nameToUpdate, newName);
        String expected = ErrMsgConstant.INVALID_INPUT;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenNonDefaultUserNameShouldDeleteAndReturnSuccessfulResponse() {
        String nameToDelete = "Bella";
        String actual  = userRepository.delete(nameToDelete);
        String expected = "User " + nameToDelete + " deleted";
        assertThat(actual, equalTo(expected));
        assertFalse(users.contains(this.bella));
    }

    @Test
    public void givenDefaultUserNameShouldNotDeleteAndReturnDefaultUserResponse() {
        String nameToDelete = "Iris";
        String actual  = userRepository.delete(nameToDelete);
        String expected = ErrMsgConstant.DEFAULT_USER;
        assertThat(actual, equalTo(expected));
        assertTrue(users.contains(this.defaultUser));
    }

    @Test
    public void givenNonExistUserNameShouldNotDeleteAndReturnUserNotExistResponse() {
        String nameToDelete = "Jane";
        String actual  = userRepository.delete(nameToDelete);
        String expected = ErrMsgConstant.USER_NOT_EXIST;
        assertThat(actual, equalTo(expected));
    }

    @Test
    public void givenNumericUserNameShouldNotDeleteAndReturnInvalidUserResponse() {
        String nameToDelete = "Jane123";
        String actual  = userRepository.delete(nameToDelete);
        String expected = ErrMsgConstant.INVALID_INPUT;
        assertThat(actual, equalTo(expected));
    }
}
