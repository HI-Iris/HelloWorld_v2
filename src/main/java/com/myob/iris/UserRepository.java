package com.myob.iris;

import java.util.List;

public interface UserRepository {
    HttpResponse create(List<User> users, String nameToCreate);

    HttpResponse read(List<User> users);

    HttpResponse update(List<User> users, String nameToUpdate, String newName);

    HttpResponse delete(List<User> users, String nameToDelete);
}
