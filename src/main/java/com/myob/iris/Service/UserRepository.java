package com.myob.iris.Service;

import com.myob.iris.Model.User;

import java.util.List;

public interface UserRepository {
    String create(String nameToCreate);

    String read();

    String update(String nameToUpdate, String newName);

    String delete(String nameToDelete);

    List<User> getUsers();
}
