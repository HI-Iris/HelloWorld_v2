package com.myob.iris;

import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {
    private List<User> users;

    public UserRepositoryImpl(List<User> users) {
        this.users = users;
    }

    @Override
    public HttpResponse create(String nameToCreate) {
        List<String> upperCaseName = getUpperCaseNamesFromUsers();
        NameCheckResult nameCheckResult = NameValidator.checkName(upperCaseName, nameToCreate);
        switch (nameCheckResult) {
            case Default_User:
                return BuildinHttpResponse.RESPONSE_DEFAULT_USER;
            case User_Exist:
                return BuildinHttpResponse.RESPONSE_USER_EXIST;
            case User_Not_Exist:
                User newUser = new User(nameToCreate);
                users.add(newUser);
                return new HttpResponse(200, "User " + nameToCreate + " created");
            case Invalid_User:
                return BuildinHttpResponse.RESPONSE_INVALID_USER;
            default:
                return BuildinHttpResponse.RESPONSE_UNEXPECTED;
        }
    }

    @Override
    public HttpResponse read() {
        List<String> names = users.stream().map(User::getName).collect(Collectors.toList());
        String nameList = String.join(", ", names);
        return new HttpResponse(200, nameList);
    }

    @Override
    public HttpResponse update(String nameToUpdate, String newName) {
        List<String> upperCaseName = getUpperCaseNamesFromUsers();
        NameCheckResult nameCheckResult = NameValidator.checkName(upperCaseName, nameToUpdate);
        switch (nameCheckResult) {
            case Default_User:
                return BuildinHttpResponse.RESPONSE_DEFAULT_USER;
            case User_Not_Exist:
                return BuildinHttpResponse.RESPONSE_USER_NOT_EXIST;
            case User_Exist:
                NameCheckResult newNameCheckResult = NameValidator.checkName(upperCaseName, newName);
                if (newNameCheckResult.equals(NameCheckResult.User_Not_Exist)) {
                    int userIndex = upperCaseName.indexOf(nameToUpdate.toUpperCase());
                    users.get(userIndex).setName(newName);
                    return new HttpResponse(200, "User " + nameToUpdate + " updated to " + newName);
                } else {
                    return BuildinHttpResponse.RESPONSE_USER_EXIST;
                }
            case Invalid_User:
                return BuildinHttpResponse.RESPONSE_INVALID_USER;
            default:
                return BuildinHttpResponse.RESPONSE_UNEXPECTED;
        }
    }

    @Override
    public HttpResponse delete(String nameToDelete) {
        List<String> upperCaseName = getUpperCaseNamesFromUsers();
        NameCheckResult nameCheckResult = NameValidator.checkName(upperCaseName, nameToDelete);
        switch (nameCheckResult) {
            case Default_User:
                return BuildinHttpResponse.RESPONSE_DEFAULT_USER;
            case User_Exist:
                int userIndex = upperCaseName.indexOf(nameToDelete.toUpperCase());
                users.remove(userIndex);
                return new HttpResponse(200, "User " + nameToDelete + " deleted");
            case User_Not_Exist:
                return BuildinHttpResponse.RESPONSE_USER_NOT_EXIST;
            case Invalid_User:
                return BuildinHttpResponse.RESPONSE_INVALID_USER;
            default:
                return BuildinHttpResponse.RESPONSE_UNEXPECTED;
        }
    }

    private List<String> getUpperCaseNamesFromUsers() {
        return users.stream().map(user -> user.getName().toUpperCase()).collect(Collectors.toList());
    }

}
