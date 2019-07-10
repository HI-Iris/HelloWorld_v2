package com.myob.iris;

import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {
    private List<User> users;

    public UserRepositoryImpl(List<User> users) {
        this.users = users;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public String create(String nameToCreate) {
        List<String> upperCaseName = getUpperCaseNamesFromUsers();
        NameCheckResult nameCheckResult = NameValidator.checkName(upperCaseName, nameToCreate);
        switch (nameCheckResult) {
            case Default_User:
                return ConstantString.DEFAULT_USER;
            case User_Exist:
                return ConstantString.USER_EXIST;
            case User_Not_Exist:
                User newUser = new User(nameToCreate);
                users.add(newUser);
                return "User " + nameToCreate + " created";
            case Invalid_User:
                return ConstantString.INVALID_INPUT;
            default:
                return ConstantString.UNEXPECTED_ERROR;
        }
    }

    @Override
    public String read() {
        List<String> names = users.stream().map(User::getName).collect(Collectors.toList());
        return String.join(", ", names);
    }

    @Override
    public String update(String nameToUpdate, String newName) {
        List<String> upperCaseName = getUpperCaseNamesFromUsers();
        NameCheckResult nameCheckResult = NameValidator.checkName(upperCaseName, nameToUpdate);
        switch (nameCheckResult) {
            case Default_User:
                return ConstantString.DEFAULT_USER;
            case User_Not_Exist:
                return ConstantString.USER_NOT_EXIST;
            case User_Exist:
                NameCheckResult newNameCheckResult = NameValidator.checkName(upperCaseName, newName);
                if (newNameCheckResult.equals(NameCheckResult.User_Not_Exist)) {
                    int userIndex = upperCaseName.indexOf(nameToUpdate.toUpperCase());
                    users.get(userIndex).setName(newName);
                    return "User " + nameToUpdate + " updated to " + newName;
                } else {
                    return ConstantString.USER_EXIST;
                }
            case Invalid_User:
                return ConstantString.INVALID_INPUT;
            default:
                return ConstantString.UNEXPECTED_ERROR;
        }
    }

    @Override
    public String delete(String nameToDelete) {
        List<String> upperCaseName = getUpperCaseNamesFromUsers();
        NameCheckResult nameCheckResult = NameValidator.checkName(upperCaseName, nameToDelete);
        switch (nameCheckResult) {
            case Default_User:
                return ConstantString.DEFAULT_USER;
            case User_Exist:
                int userIndex = upperCaseName.indexOf(nameToDelete.toUpperCase());
                users.remove(userIndex);
                return "User " + nameToDelete + " deleted";
            case User_Not_Exist:
                return ConstantString.USER_NOT_EXIST;
            case Invalid_User:
                return ConstantString.INVALID_INPUT;
            default:
                return ConstantString.UNEXPECTED_ERROR;
        }
    }

    private List<String> getUpperCaseNamesFromUsers() {
        return users.stream().map(user -> user.getName().toUpperCase()).collect(Collectors.toList());
    }

}
