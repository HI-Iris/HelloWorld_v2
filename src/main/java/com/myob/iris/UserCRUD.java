package com.myob.iris;

import java.util.ArrayList;
import java.util.List;

public class UserCRUD {
        private List<String> getUpperCaseNamesFromUsers(List<User> users){
        List<String> names = new ArrayList<>();
        for (User user: users) {
            names.add(user.getName().toUpperCase());
        }
        return names;
    }
    public CRUDResult create(String nameWantToCreate) {
        return new CRUDResult(false, "");
    }

    public CRUDResult read() {
        return new CRUDResult(false, "");
    }

    public CRUDResult update(List<User> users, String nameWantToUpdate) {
        return new CRUDResult(false, "");
    }

    public CRUDResult delete(List<User> users, String nameToDelete) {
        List<String> names = getUpperCaseNamesFromUsers(users);
        if (nameToDelete.toUpperCase().equals(names.get(0))) {
            return new CRUDResult(false, "");
        } else if (!names.contains(nameToDelete.toUpperCase())) {
            return new CRUDResult(false, "");
        } else {
            int index = names.indexOf(nameToDelete.toUpperCase());
            users.remove(index);
            return new CRUDResult(true, "");
        }
    }
}
