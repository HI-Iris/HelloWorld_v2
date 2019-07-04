package com.myob.iris;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserHandler extends Handler {

    private List<User> users;
    private UserRepository userRepository;

    public UserHandler(List<User> users, UserRepository userRepository) {
        this.users = users;
        this.userRepository = userRepository;
    }

    public HttpResponse getHttpResponse(String requestMethod, Optional<Map<String, String>> params) {
        HttpResponse httpResponse;
        switch (requestMethod.toUpperCase()) {
            case "POST":
                if (params.isPresent() && params.get().containsKey("name")) {
                    httpResponse = userRepository.create(this.users, params.get().get("name"));
                } else {
                    httpResponse = BuildInHttpResponse.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            case "GET":
                httpResponse = userRepository.read(this.users);
                break;
            case "PUT":
                if (params.isPresent() && params.get().containsKey("name") && params.get().containsKey("newName")) {
                    httpResponse = userRepository.update(this.users, params.get().get("name"), params.get().get("newName"));
                } else {
                    httpResponse = BuildInHttpResponse.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            case "DELETE":
                if (params.isPresent() && params.get().containsKey("name")) {
                    httpResponse = userRepository.delete(this.users, params.get().get("name"));
                } else {
                    httpResponse = BuildInHttpResponse.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            default:
                httpResponse = BuildInHttpResponse.RESPONSE_REQUEST_NOT_IMPLEMENTED;
                break;
        }
        return httpResponse;
    }

}

