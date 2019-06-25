package com.myob.iris;

import java.util.ArrayList;
import java.util.List;

public class UserCRUD {
    private int userIndex;
    private NameCheckResult nameCheckResult;

    public CRUDResult create(List<User> users, String nameToCreate) {
        this.nameCheckResult = checkName(users, nameToCreate);
        switch (nameCheckResult) {
            case Default_User:
                return new CRUDResult(false, Constance.ERROR_DEFAULT_USER);
            case User_Exist:
                return new CRUDResult(false, Constance.ERROR_USER_EXIST);
            case User_Not_Exist:
                User newUser = new User(nameToCreate);
                users.add(newUser);
                return new CRUDResult(true, Constance.USER_CREATED);
            default:
                return new CRUDResult(false, Constance.ERROR_UNEXPECTED);
        }
    }

    public CRUDResult read() {
        return new CRUDResult(false, "");
    }

    public CRUDResult update(List<User> users, String nameToUpdate, String newName) {
        this.nameCheckResult = checkName(users, nameToUpdate);
        switch (nameCheckResult) {
            case Default_User:
                return new CRUDResult(false, Constance.ERROR_DEFAULT_USER);
            case User_Not_Exist:
                return new CRUDResult(false, Constance.ERROR_USER_NOT_EXIST);
            case User_Exist:
                NameCheckResult newNameCheckResult = checkName(users, newName);
                if (newNameCheckResult.equals(NameCheckResult.User_Not_Exist)) {
                    users.get(this.userIndex).setName(newName);
                    return new CRUDResult(true, Constance.USER_UPDATED);
                } else {
                    return new CRUDResult(false, Constance.ERROR_USER_EXIST);
                }
            default:
                return new CRUDResult(false, Constance.ERROR_UNEXPECTED);
        }

    }

    public CRUDResult delete(List<User> users, String nameToDelete) {
        this.nameCheckResult = checkName(users, nameToDelete);
        switch (nameCheckResult) {
            case Default_User:
                return new CRUDResult(false, Constance.ERROR_DEFAULT_USER);
            case User_Exist:
                users.remove(this.userIndex);
                return new CRUDResult(true, Constance.USER_DELETED);
            case User_Not_Exist:
                return new CRUDResult(false, Constance.ERROR_USER_NOT_EXIST);
            default:
                return new CRUDResult(false, Constance.ERROR_UNEXPECTED);
        }
    }

    public NameCheckResult checkName(List<User> users, String nameToExam) {
        List<String> names = getUpperCaseNamesFromUsers(users);
        if (nameToExam.toUpperCase().equals(names.get(0))) {
            return NameCheckResult.Default_User;
        } else if (names.contains(nameToExam.toUpperCase())) {
            this.userIndex = names.indexOf(nameToExam.toUpperCase());
            return NameCheckResult.User_Exist;
        } else {
            return NameCheckResult.User_Not_Exist;
        }
    }

    private List<String> getUpperCaseNamesFromUsers(List<User> users) {
        List<String> names = new ArrayList<>();
        for (User user : users) {
            names.add(user.getName().toUpperCase());
        }
        return names;
    }
}
