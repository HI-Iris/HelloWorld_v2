package com.myob.iris;

public interface UserRepository {
    String create(String nameToCreate);

    String read();

    String update(String nameToUpdate, String newName);

    String delete(String nameToDelete);
}
