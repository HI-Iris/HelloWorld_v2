package com.myob.iris;

import java.util.List;
import java.util.stream.Collectors;

public class UserDaoImpl implements UserDao {

    @Override
    public HttpResponse create(List<User> users, String nameToCreate) {
        List<String> upperCaseName = getUpperCaseNamesFromUsers(users);
        NameCheckResult nameCheckResult = NameValidator.checkName(upperCaseName, nameToCreate);
        switch (nameCheckResult) {
            case Default_User:
                return new HttpResponse(404, Constant.ERROR_DEFAULT_USER);
            case User_Exist:
                return new HttpResponse(404, Constant.ERROR_USER_EXIST);
            case User_Not_Exist:
                User newUser = new User(nameToCreate);
                users.add(newUser);
                return new HttpResponse(200, Constant.USER_CREATED);
            default:
                return new HttpResponse(404, Constant.ERROR_UNEXPECTED);
        }
    }

    @Override
    public HttpResponse read(List<User> users) {
        List<String> names = users.stream().map(User::getName).collect(Collectors.toList());
        String nameList = String.join(", ", names);
        return new HttpResponse(200, Constant.USER_READ);
    }

    @Override
    public HttpResponse update(List<User> users, String nameToUpdate, String newName) {
        List<String> upperCaseName = getUpperCaseNamesFromUsers(users);
        NameCheckResult nameCheckResult = NameValidator.checkName(upperCaseName, nameToUpdate);
        switch (nameCheckResult) {
            case Default_User:
                return new HttpResponse(404, Constant.ERROR_DEFAULT_USER);
            case User_Not_Exist:
                return new HttpResponse(404, Constant.ERROR_USER_NOT_EXIST);
            case User_Exist:
                NameCheckResult newNameCheckResult = NameValidator.checkName(upperCaseName, newName);
                if (newNameCheckResult.equals(NameCheckResult.User_Not_Exist)) {
                    int userIndex = upperCaseName.indexOf(nameToUpdate.toUpperCase());
                    users.get(userIndex).setName(newName);
                    return new HttpResponse(200, Constant.USER_UPDATED);
                } else {
                    return new HttpResponse(404, Constant.ERROR_USER_EXIST);
                }
            default:
                return new HttpResponse(404, Constant.ERROR_UNEXPECTED);
        }
    }

    @Override
    public HttpResponse delete(List<User> users, String nameToDelete) {
        List<String> upperCaseName = getUpperCaseNamesFromUsers(users);
        NameCheckResult nameCheckResult = NameValidator.checkName(upperCaseName, nameToDelete);
        switch (nameCheckResult) {
            case Default_User:
                return new HttpResponse(404, Constant.ERROR_DEFAULT_USER);
            case User_Exist:
                int userIndex = upperCaseName.indexOf(nameToDelete.toUpperCase());
                users.remove(userIndex);
                return new HttpResponse(200, Constant.USER_DELETED);
            case User_Not_Exist:
                return new HttpResponse(404, Constant.ERROR_USER_NOT_EXIST);
            default:
                return new HttpResponse(404, Constant.ERROR_UNEXPECTED);
        }
    }

    private List<String> getUpperCaseNamesFromUsers(List<User> users){
        return users.stream().map(user -> user.getName().toUpperCase()).collect(Collectors.toList());
    }

}
