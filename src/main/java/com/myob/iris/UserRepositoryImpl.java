package com.myob.iris;

import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public HttpResponse create(List<User> users, String nameToCreate) {
        List<String> upperCaseName = getUpperCaseNamesFromUsers(users);
        NameCheckResult nameCheckResult = NameValidator.checkName(upperCaseName, nameToCreate);
        switch (nameCheckResult) {
            case Default_User:
                return BuildInHttpResponse.RESPONSE_DEFAULT_USER;
            case User_Exist:
                return BuildInHttpResponse.RESPONSE_USER_EXIST;
            case User_Not_Exist:
                User newUser = new User(nameToCreate);
                users.add(newUser);
                return new HttpResponse(200, "User " + nameToCreate + " created");
            default:
                return BuildInHttpResponse.RESPONSE_UNEXPECTED;
        }
    }

    @Override
    public HttpResponse read(List<User> users) {
        List<String> names = users.stream().map(User::getName).collect(Collectors.toList());
        String nameList = String.join(", ", names);
        return new HttpResponse(200, nameList);
    }

    @Override
    public HttpResponse update(List<User> users, String nameToUpdate, String newName) {
        List<String> upperCaseName = getUpperCaseNamesFromUsers(users);
        NameCheckResult nameCheckResult = NameValidator.checkName(upperCaseName, nameToUpdate);
        switch (nameCheckResult) {
            case Default_User:
                return BuildInHttpResponse.RESPONSE_DEFAULT_USER;
            case User_Not_Exist:
                return BuildInHttpResponse.RESPONSE_USER_NOT_EXIST;
            case User_Exist:
                NameCheckResult newNameCheckResult = NameValidator.checkName(upperCaseName, newName);
                if (newNameCheckResult.equals(NameCheckResult.User_Not_Exist)) {
                    int userIndex = upperCaseName.indexOf(nameToUpdate.toUpperCase());
                    users.get(userIndex).setName(newName);
                    return new HttpResponse(200, "User " + nameToUpdate + " updated to " + newName);
                } else {
                    return BuildInHttpResponse.RESPONSE_USER_EXIST;
                }
            default:
                return BuildInHttpResponse.RESPONSE_UNEXPECTED;
        }
    }

    @Override
    public HttpResponse delete(List<User> users, String nameToDelete) {
        List<String> upperCaseName = getUpperCaseNamesFromUsers(users);
        NameCheckResult nameCheckResult = NameValidator.checkName(upperCaseName, nameToDelete);
        switch (nameCheckResult) {
            case Default_User:
                return BuildInHttpResponse.RESPONSE_DEFAULT_USER;
            case User_Exist:
                int userIndex = upperCaseName.indexOf(nameToDelete.toUpperCase());
                users.remove(userIndex);
                return new HttpResponse(200, "User " + nameToDelete + " deleted");
            case User_Not_Exist:
                return BuildInHttpResponse.RESPONSE_USER_NOT_EXIST;
            default:
                return BuildInHttpResponse.RESPONSE_UNEXPECTED;
        }
    }

    private List<String> getUpperCaseNamesFromUsers(List<User> users) {
        return users.stream().map(user -> user.getName().toUpperCase()).collect(Collectors.toList());
    }

}
