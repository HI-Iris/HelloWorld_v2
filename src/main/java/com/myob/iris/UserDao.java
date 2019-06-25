package com.myob.iris;

import java.util.List;

public interface UserDao {
    CRUDResult create(List<User> users, String nameToCreate);

    CRUDResult read(List<User> users);

    CRUDResult update(List<User> users, String nameToUpdate, String newName);

    CRUDResult delete(List<User> users, String nameToDelete);
}
