package com.myob.iris;

import java.util.ArrayList;
import java.util.List;

public class UserCRUD {
    int userIndex;

    public CRUDResult create(String nameToCreate) {
        return new CRUDResult(false, "");
    }

    public CRUDResult read() {
        return new CRUDResult(false, "");
    }

    public CRUDResult update(List<User> users, String nameToUpdate, String newName) {
        return new CRUDResult(false, "");
    }

    public CRUDResult delete(List<User> users, String nameToDelete) {
        NameExamResult nameExamResult = getNameExamResult(nameToDelete, users);
        switch (nameExamResult) {
            case Default_User:
                return new CRUDResult(false, Constance.DEFAULT_USER);
            case User_Exist:
                users.remove(this.userIndex);
                return new CRUDResult(true, "");
            case User_Not_Exist:
                return new CRUDResult(false, Constance.USER_NOT_EXIST);
            default:
                return new CRUDResult(false, "unexpected");
        }
    }

    public NameExamResult getNameExamResult(String nameToExam, List<User> users) {
        List<String> names = getUpperCaseNamesFromUsers(users);
        if (nameToExam.toUpperCase().equals(names.get(0))) {
            return NameExamResult.Default_User;
        } else if (names.contains(nameToExam.toUpperCase())) {
            this.userIndex = names.indexOf(nameToExam.toUpperCase());
            return NameExamResult.User_Exist;
        } else {
            return NameExamResult.User_Not_Exist;
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
