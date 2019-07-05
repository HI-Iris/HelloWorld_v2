package com.myob.iris;

public interface UserRepository {
    HttpResponse create(String nameToCreate);

    HttpResponse read();

    HttpResponse update(String nameToUpdate, String newName);

    HttpResponse delete(String nameToDelete);
}
