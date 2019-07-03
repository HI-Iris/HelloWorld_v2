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
    private UserRepositoryImpl userDaoImpl;
    private HttpResponse httpResponse;
    private final User defaultUser = new User("Iris");
    private User bella = new User("Bella");
    private User paul = new User("Paul");

    @Before
    public void setup() {
        userDaoImpl = new UserRepositoryImpl();
        httpResponse = new HttpResponse(404, "");
        users = new ArrayList<>();
        users.add(defaultUser);
        users.add(bella);
        users.add(paul);
    }


    @Test
    public void giveDefaultUserNameShouldNotDelete() {
        String nameToDelete = "Iris";
        httpResponse = userDaoImpl.delete(users, nameToDelete);
        HttpResponse expected = Constant.RESPONSE_DEFAULT_USER;
        assertThat(httpResponse, equalTo(expected));
        assertTrue(users.contains(this.defaultUser));
    }

    @Test
    public void giveNonDefaultUserNameShouldDelete() {
        String nameToDelete = "Bella";
        httpResponse = userDaoImpl.delete(users, nameToDelete);
        HttpResponse expected = new HttpResponse(200, "User " + nameToDelete + " deleted");
        assertThat(httpResponse, equalTo(expected));
        assertFalse(users.contains(this.bella));
    }

    @Test
    public void giveNonExistUserNameShouldNotDelete() {
        String nameToDelete = "Jane";
        httpResponse = userDaoImpl.delete(users, nameToDelete);
        HttpResponse expected = Constant.RESPONSE_USER_NOT_EXIST;
        assertThat(httpResponse, equalTo(expected));
    }

    @Test
    public void giveNonExistUserNameShouldCreateUser() {
        String nameToCreate = "Lee";
        httpResponse = userDaoImpl.create(users, nameToCreate);
        HttpResponse expected = new HttpResponse(200, "User " + nameToCreate + " created");
        assertThat(httpResponse, equalTo(expected));
        assertThat(users.get(3).getName(), equalTo("Lee"));
    }

    @Test
    public void giveDefaultUserNameShouldNotCreateUser() {
        String nameToCreate = "iris";
        httpResponse = userDaoImpl.create(users, nameToCreate);
        HttpResponse expected = Constant.RESPONSE_DEFAULT_USER;
        assertThat(httpResponse, equalTo(expected));
    }

    @Test
    public void giveExistUserNameShouldNotCreateUser() {
        String nameToCreate = "bella";
        httpResponse = userDaoImpl.create(users, nameToCreate);
        HttpResponse expected = Constant.RESPONSE_USER_EXIST;
        assertThat(httpResponse, equalTo(expected));
    }

    @Test
    public void giveDefaultUserShouldNotUpdate() {
        String nameToUpdate = defaultUser.getName();
        String newName = "Jane";
        httpResponse = userDaoImpl.update(users, nameToUpdate, newName);
        HttpResponse expected = Constant.RESPONSE_DEFAULT_USER;
        assertThat(httpResponse, equalTo(expected));
    }

    @Test
    public void giveExistUserAndNewNameShouldUpdate() {
        String nameToUpdate = users.get(2).getName();
        String newName = "Jane";
        httpResponse = userDaoImpl.update(users, nameToUpdate, newName);
        HttpResponse expected = new HttpResponse(200, "User " + nameToUpdate + " updated to " + newName);
        assertThat(httpResponse, equalTo(expected));
        assertThat(users.get(2).getName(), equalTo("Jane"));
    }

    @Test
    public void giveExistUserAndExistNameShouldNotUpdate() {
        String nameToUpdate = users.get(2).getName();
        String newName = "bella";
        httpResponse = userDaoImpl.update(users, nameToUpdate, newName);
        HttpResponse expected = Constant.RESPONSE_USER_EXIST;
        assertThat(httpResponse, equalTo(expected));
    }

    @Test
    public void giveNotExistUserShouldNotUpdate() {
        String nameToUpdate = "Jane";
        String newName = "bella";
        httpResponse = userDaoImpl.update(users, nameToUpdate, newName);
        HttpResponse expected = Constant.RESPONSE_USER_NOT_EXIST;
        assertThat(httpResponse, equalTo(expected));
    }
}
