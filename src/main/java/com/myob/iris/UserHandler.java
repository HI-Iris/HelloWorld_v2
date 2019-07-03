package com.myob.iris;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserHandler extends HttpResponseSender {

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
                //TODO isPresent() is it necessary?
                if (params.isPresent() && params.get().containsKey("name")) {
                    httpResponse = userRepository.create(this.users, params.get().get("name"));
                } else {
                    httpResponse = HttpResult.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            case "GET":
                httpResponse = userRepository.read(this.users);
                break;
            case "DELETE":
                if (params.isPresent() && params.get().get("name") != null) {
                    httpResponse = userRepository.delete(this.users, params.get().get("name"));
                } else {
                    httpResponse = HttpResult.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            case "PUT":
                if (params.isPresent() && params.get().get("name") != null && params.get().get("newName") != null) {
                    httpResponse = userRepository.update(this.users, params.get().get("name"), params.get().get("newName"));
                } else {
                    httpResponse = HttpResult.RESPONSE_PARAMETER_NOT_MATCH;
                }
                break;
            default:
                httpResponse = HttpResult.RESPONSE_REQUEST_NOT_IMPLEMENTED;
                break;
        }
        return httpResponse;
    }

}

