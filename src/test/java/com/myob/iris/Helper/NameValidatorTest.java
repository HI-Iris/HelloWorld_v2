package com.myob.iris.Helper;

import com.myob.iris.Helper.NameValidator;
import com.myob.iris.Model.NameCheckResult;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class NameValidatorTest {
    private NameCheckResult nameCheckResult;
    private static List<String> names;

    @BeforeClass
    public static void setUp() {
        names = new ArrayList<>();
        names.add("IRIS");
        names.add("BELLA");
        names.add("PAUL");
    }

    @Test
    public void givenDefaultUserInLowerCaseShouldReturnDefault() {
        String nameToCheck = "iris";
        this.nameCheckResult = NameValidator.checkName(names, nameToCheck);
        assertThat(this.nameCheckResult, equalTo(NameCheckResult.Default_User));
    }

    @Test
    public void givenDefaultUserInRandomCaseShouldReturnDefault() {
        String nameToCheck = "iRiS";
        this.nameCheckResult = NameValidator.checkName(names, nameToCheck);
        assertThat(this.nameCheckResult, equalTo(NameCheckResult.Default_User));
    }

    @Test
    public void givenUserInLowerCaseInListShouldReturnExist() {
        String nameToCheck = "bella";
        this.nameCheckResult = NameValidator.checkName(names, nameToCheck);
        assertThat(this.nameCheckResult, equalTo(NameCheckResult.User_Exist));
    }

    @Test
    public void givenUserInRandomCaseInListShouldReturnExist() {
        String nameToCheck = "bEllA";
        this.nameCheckResult = NameValidator.checkName(names, nameToCheck);
        assertThat(this.nameCheckResult, equalTo(NameCheckResult.User_Exist));
    }

    @Test
    public void givenUserNotInListShouldReturnNotExist() {
        String nameToCheck = "no in list";
        this.nameCheckResult = NameValidator.checkName(names, nameToCheck);
        assertThat(this.nameCheckResult, equalTo(NameCheckResult.User_Not_Exist));
    }

}