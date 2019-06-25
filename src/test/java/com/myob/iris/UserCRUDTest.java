package com.myob.iris;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

public class UserCRUDTest {

    private List<User> users;
    private UserCRUD userCRUD;
    private CRUDResult crudResult;
    private NameCheckResult nameCheckResult;
    private final User defaultUser = new User("Iris");
    private User bella = new User("Bella");
    private User paul = new User("Paul");

    @Before
    public void setup() {
        userCRUD = new UserCRUD();
        crudResult = new CRUDResult(false, "");
        users = new ArrayList<>();
        users.add(defaultUser);
        users.add(bella);
        users.add(paul);
    }

    @Test
    public void giveDefaultUserInLowerCaseShouldReturnDefault(){
        String nameToExam ="iris";
        this.nameCheckResult = userCRUD.checkName(users, nameToExam);
        assertThat(this.nameCheckResult, equalTo(NameCheckResult.Default_User));
    }

    @Test
    public void giveDefaultUserInRandomCaseShouldReturnDefault(){
        String nameToExam ="iRiS";
        this.nameCheckResult = userCRUD.checkName(users, nameToExam);
        assertThat(this.nameCheckResult, equalTo(NameCheckResult.Default_User));
    }

    @Test
    public void giveUserInLowerCaseInListShouldReturnExist(){
        String nameToExam ="bella";
        this.nameCheckResult = userCRUD.checkName(users, nameToExam);
        assertThat(this.nameCheckResult, equalTo(NameCheckResult.User_Exist));
    }

    @Test
    public void giveUserInRandomCaseInListShouldReturnExist(){
        String nameToExam ="bEllA";
        this.nameCheckResult = userCRUD.checkName(users, nameToExam);
        assertThat(this.nameCheckResult, equalTo(NameCheckResult.User_Exist));
    }

    @Test
    public void giveUserNotInListShouldReturnNotExist(){
        String nameToExam ="no in list";
        this.nameCheckResult = userCRUD.checkName(users, nameToExam);
        assertThat(this.nameCheckResult, equalTo(NameCheckResult.User_Not_Exist));
    }

    @Test
    public void giveDefaultUserNameShouldNotDelete() {
        String nameToDelete = "Iris";
        crudResult = userCRUD.delete(users, nameToDelete);
        CRUDResult expected = new CRUDResult(false, Constance.ERROR_DEFAULT_USER);
        assertThat(crudResult, equalTo(expected));
        assertTrue(users.contains(this.defaultUser));
    }

    @Test
    public void giveNonDefaultUserNameShouldDelete() {
        String nameToDelete = "Bella";
        crudResult = userCRUD.delete(users, nameToDelete);
        CRUDResult expected = new CRUDResult(true, Constance.USER_DELETED);
        assertThat(crudResult, equalTo(expected));
        assertFalse(users.contains(this.bella));
    }

    @Test
    public void giveNonExistUserNameShouldNotDelete() {
        String nameToDelete = "Jane";
        crudResult = userCRUD.delete(users, nameToDelete);
        CRUDResult expected = new CRUDResult(false, Constance.ERROR_USER_NOT_EXIST);
        assertThat(crudResult, equalTo(expected));
    }

    @Test
    public void giveNonExistUserNameShouldCreateUser(){
        String nameToCreate = "Lee";
        crudResult = userCRUD.create(users, nameToCreate);
        CRUDResult expected = new CRUDResult(true, Constance.USER_CREATED);
        assertThat(crudResult, equalTo(expected));
        assertThat(users.get(3).getName(), equalTo("Lee"));
    }

    @Test
    public void giveDefaultUserNameShouldNotCreateUser(){
        String nameToCreate = "iris";
        crudResult = userCRUD.create(users, nameToCreate);
        CRUDResult expected = new CRUDResult(false, Constance.ERROR_DEFAULT_USER);
        assertThat(crudResult, equalTo(expected));
    }

    @Test
    public void giveExistUserNameShouldNotCreateUser(){
        String nameToCreate = "bella";
        crudResult = userCRUD.create(users, nameToCreate);
        CRUDResult expected = new CRUDResult(false, Constance.ERROR_USER_EXIST);
        assertThat(crudResult, equalTo(expected));
    }

    @Test
    public void giveDefaultUserShouldNotUpdate(){
        String nameToUpdate = defaultUser.getName();
        String newName = "Jane";
        crudResult = userCRUD.update(users,nameToUpdate,newName);
        CRUDResult expected = new CRUDResult(false, Constance.ERROR_DEFAULT_USER);
        assertThat(crudResult, equalTo(expected));
    }

    @Test
    public void giveExistUserAndNewNameShouldUpdate(){
        String nameToUpdate = users.get(2).getName();
        String newName = "Jane";
        crudResult = userCRUD.update(users,nameToUpdate,newName);
        CRUDResult expected = new CRUDResult(true, Constance.USER_UPDATED);
        assertThat(crudResult, equalTo(expected));
        assertThat(users.get(2).getName(), equalTo("Jane"));
    }

    @Test
    public void giveExistUserAndExistNameShouldNotUpdate(){
        String nameToUpdate = users.get(2).getName();
        String newName = "bella";
        crudResult = userCRUD.update(users,nameToUpdate,newName);
        CRUDResult expected = new CRUDResult(false, Constance.ERROR_USER_EXIST);
        assertThat(crudResult, equalTo(expected));
    }

    @Test
    public void giveNotExistUserShouldNotUpdate(){
        String nameToUpdate = "Jane";
        String newName = "bella";
        crudResult = userCRUD.update(users,nameToUpdate,newName);
        CRUDResult expected = new CRUDResult(false, Constance.ERROR_USER_NOT_EXIST);
        assertThat(crudResult, equalTo(expected));
    }
}
