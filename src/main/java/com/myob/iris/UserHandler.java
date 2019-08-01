package com.myob.iris;

import java.util.Map;
import java.util.Optional;

public class UserHandler extends Handler {

    private UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public HttpResponse fulfilRequest(String requestMethod, Optional<Map<String, String>> params) {
        switch (requestMethod.toUpperCase()) {
            case "POST":
                if (params.isPresent() && params.get().containsKey("name")) {
                    return new HttpResponse(200, userRepository.create(params.get().get("name")));
                } else {
                    return new HttpResponse(404, ErrMsgConstant.PARAMETER_NOT_FOUND);
                }
            case "GET":
                return new HttpResponse(200, userRepository.read());
            case "PUT":
                if (params.isPresent() && params.get().containsKey("name") && params.get().containsKey("newName")) {
                    return new HttpResponse(200, userRepository.update(params.get().get("name"), params.get().get("newName")));
                } else {
                    return new HttpResponse(404, ErrMsgConstant.PARAMETER_NOT_FOUND);
                }
            case "DELETE":
                if (params.isPresent() && params.get().containsKey("name")) {
                    return new HttpResponse(200, userRepository.delete(params.get().get("name")));
                } else {
                    return new HttpResponse(404, ErrMsgConstant.PARAMETER_NOT_FOUND);
                }
            default:
                return new HttpResponse(501, ErrMsgConstant.REQUEST_NOT_IMPLEMENTED);
        }
    }


}

