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
    public void giveDefaultUserNameShouldNotDelete() {
        String nameToDelete = "Iris";
        crudResult = userCRUD.delete(users, nameToDelete);
        CRUDResult expected = new CRUDResult(false, Constance.DEFAULT_USER);
        assertThat(crudResult, equalTo(expected));
        assertTrue(users.contains(this.defaultUser));
    }

    @Test
    public void giveNonDefaultUserNameShouldDelete() {
        String nameToDelete = "Bella";
        crudResult = userCRUD.delete(users, nameToDelete);
        CRUDResult expected = new CRUDResult(true, "");
        assertThat(crudResult, equalTo(expected));
        assertFalse(users.contains(this.bella));
    }

    @Test
    public void giveNonExistingUserNameShouldNotDelete() {
        String nameToDelete = "Jane";
        crudResult = userCRUD.delete(users, nameToDelete);
        CRUDResult expected = new CRUDResult(false, Constance.USER_NOT_EXIST);
        assertThat(crudResult, equalTo(expected));
    }

    @Test
    public void userIsDefault(){
        String nameToExam ="iris";
        NameExamResult result = userCRUD.getNameExamResult(nameToExam,users);
        assertThat(result, equalTo(NameExamResult.Default_User));
    }
    @Test
    public void userExist(){
        String nameToExam ="bella";
        NameExamResult result = userCRUD.getNameExamResult(nameToExam,users);
        assertThat(result, equalTo(NameExamResult.User_Exist));
    }

    @Test
    public void userDoesNotExist(){
        String nameToExam ="no in list";
        NameExamResult result = userCRUD.getNameExamResult(nameToExam, users);
        assertThat(result, equalTo(NameExamResult.User_Not_Exist));
    }

}
