package com.myob.iris;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

public class UserDaoImplTest {

    private List<User> users;
    private UserDaoImpl userDaoImpl;
    private CRUDResult crudResult;
    private final User defaultUser = new User("Iris");
    private User bella = new User("Bella");
    private User paul = new User("Paul");

    @Before
    public void setup() {
        userDaoImpl = new UserDaoImpl();
        crudResult = new CRUDResult(false, "");
        users = new ArrayList<>();
        users.add(defaultUser);
        users.add(bella);
        users.add(paul);
    }


    @Test
    public void giveDefaultUserNameShouldNotDelete() {
        String nameToDelete = "Iris";
        crudResult = userDaoImpl.delete(users, nameToDelete);
        CRUDResult expected = new CRUDResult(false, Constant.ERROR_DEFAULT_USER);
        assertThat(crudResult, equalTo(expected));
        assertTrue(users.contains(this.defaultUser));
    }

    @Test
    public void giveNonDefaultUserNameShouldDelete() {
        String nameToDelete = "Bella";
        crudResult = userDaoImpl.delete(users, nameToDelete);
        CRUDResult expected = new CRUDResult(true, Constant.USER_DELETED);
        assertThat(crudResult, equalTo(expected));
        assertFalse(users.contains(this.bella));
    }

    @Test
    public void giveNonExistUserNameShouldNotDelete() {
        String nameToDelete = "Jane";
        crudResult = userDaoImpl.delete(users, nameToDelete);
        CRUDResult expected = new CRUDResult(false, Constant.ERROR_USER_NOT_EXIST);
        assertThat(crudResult, equalTo(expected));
    }

    @Test
    public void giveNonExistUserNameShouldCreateUser() {
        String nameToCreate = "Lee";
        crudResult = userDaoImpl.create(users, nameToCreate);
        CRUDResult expected = new CRUDResult(true, Constant.USER_CREATED);
        assertThat(crudResult, equalTo(expected));
        assertThat(users.get(3).getName(), equalTo("Lee"));
    }

    @Test
    public void giveDefaultUserNameShouldNotCreateUser() {
        String nameToCreate = "iris";
        crudResult = userDaoImpl.create(users, nameToCreate);
        CRUDResult expected = new CRUDResult(false, Constant.ERROR_DEFAULT_USER);
        assertThat(crudResult, equalTo(expected));
    }

    @Test
    public void giveExistUserNameShouldNotCreateUser() {
        String nameToCreate = "bella";
        crudResult = userDaoImpl.create(users, nameToCreate);
        CRUDResult expected = new CRUDResult(false, Constant.ERROR_USER_EXIST);
        assertThat(crudResult, equalTo(expected));
    }

    @Test
    public void giveDefaultUserShouldNotUpdate() {
        String nameToUpdate = defaultUser.getName();
        String newName = "Jane";
        crudResult = userDaoImpl.update(users, nameToUpdate, newName);
        CRUDResult expected = new CRUDResult(false, Constant.ERROR_DEFAULT_USER);
        assertThat(crudResult, equalTo(expected));
    }

    @Test
    public void giveExistUserAndNewNameShouldUpdate() {
        String nameToUpdate = users.get(2).getName();
        String newName = "Jane";
        crudResult = userDaoImpl.update(users, nameToUpdate, newName);
        CRUDResult expected = new CRUDResult(true, Constant.USER_UPDATED);
        assertThat(crudResult, equalTo(expected));
        assertThat(users.get(2).getName(), equalTo("Jane"));
    }

    @Test
    public void giveExistUserAndExistNameShouldNotUpdate() {
        String nameToUpdate = users.get(2).getName();
        String newName = "bella";
        crudResult = userDaoImpl.update(users, nameToUpdate, newName);
        CRUDResult expected = new CRUDResult(false, Constant.ERROR_USER_EXIST);
        assertThat(crudResult, equalTo(expected));
    }

    @Test
    public void giveNotExistUserShouldNotUpdate() {
        String nameToUpdate = "Jane";
        String newName = "bella";
        crudResult = userDaoImpl.update(users, nameToUpdate, newName);
        CRUDResult expected = new CRUDResult(false, Constant.ERROR_USER_NOT_EXIST);
        assertThat(crudResult, equalTo(expected));
    }
}
