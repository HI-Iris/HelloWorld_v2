package com.myob.iris;

public class Constant {
    //TODO httpResponse
    public static final String ERROR_DEFAULT_USER = "Default user cannot be changed";
    public static final String ERROR_USER_NOT_EXIST = "User doesn't exist";
    public static final String ERROR_USER_EXIST = "User already exist in the list";
    public static final String ERROR_UNEXPECTED = "Unexpected error";

    public static final HttpResponse RESPONSE_REQUEST_NOT_IMPLEMENTED = new HttpResponse(501, "Request Not Implemented");
    public static final HttpResponse RESPONSE_PARAMETER_NOT_MATCH = new HttpResponse(404, "Need params");


}
