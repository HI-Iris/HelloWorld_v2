package com.myob.iris;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserHandler extends HttpResponseSender {

    private List<User> users;
    private UserRepositoryImpl userRepository;

    public UserHandler(List<User> users, UserRepositoryImpl userRepository) {
        this.users = users;
        this.userRepository = userRepository;
    }

    public HttpResponse getHttpResponse(String requestMethod, Optional<Map<String, String>> params) {
        HttpResponse httpResponse;
        switch (requestMethod.toUpperCase()) {
            case "POST":
                if (params.isPresent() && params.get().get("name") != null) {
                    httpResponse = userRepository.create(this.users, params.get().get("name"));
                } else {
                    httpResponse = Constant.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            case "GET":
                httpResponse = userRepository.read(this.users);
                break;
            case "DELETE":
                if (params.isPresent() && params.get().get("name") != null) {
                    httpResponse = userRepository.delete(this.users, params.get().get("name"));
                } else {
                    httpResponse = Constant.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            case "PUT":
                if (params.isPresent() && params.get().get("name") != null && params.get().get("newName") != null) {
                    httpResponse = userRepository.update(this.users, params.get().get("name"), params.get().get("newName"));
                } else {
                    httpResponse = Constant.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            default:
                httpResponse = Constant.RESPONSE_REQUEST_NOT_IMPLEMENTED;

                break;
        }
        return httpResponse;
    }

}

