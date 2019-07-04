package com.myob.iris;

public class BuildInHttpResponse {
    public static final HttpResponse RESPONSE_DEFAULT_USER = new HttpResponse(400, "Default user cannot be changed");
    public static final HttpResponse RESPONSE_USER_NOT_EXIST = new HttpResponse(400, "User doesn't exist");
    public static final HttpResponse RESPONSE_USER_EXIST = new HttpResponse(400, "User already exist in the list");
    public static final HttpResponse RESPONSE_UNEXPECTED = new HttpResponse(400, "Unexpected error");
    public static final HttpResponse RESPONSE_REQUEST_NOT_IMPLEMENTED = new HttpResponse(501, "Request Not Implemented");
    public static final HttpResponse RESPONSE_PARAMETER_NOT_MATCH = new HttpResponse(404, "Need params");
}
