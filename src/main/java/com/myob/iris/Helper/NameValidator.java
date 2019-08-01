package com.myob.iris.Helper;

import com.myob.iris.Model.NameCheckResult;

import java.util.List;

public class NameValidator {

    public static NameCheckResult checkName(List<String> names, String nameToCheck) {
        if (nameToCheck.equalsIgnoreCase(names.get(0))) {
            return NameCheckResult.Default_User;
        } else if (names.contains(nameToCheck.toUpperCase())) {
            return NameCheckResult.User_Exist;
        } else if (nameToCheck.matches(".*\\d.*")) {
            return NameCheckResult.Invalid_User;
        } else {
            return NameCheckResult.User_Not_Exist;
        }
    }
}
