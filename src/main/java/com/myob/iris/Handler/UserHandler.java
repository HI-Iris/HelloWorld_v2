package com.myob.iris.Handler;

import com.myob.iris.Model.ErrMsgConstant;
import com.myob.iris.Model.HttpResponse;
import com.myob.iris.Service.UserRepository;

import java.util.Map;
import java.util.Optional;

public class UserHandler extends Handler {

    private UserRepository userRepository;

    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public HttpResponse fulfilRequest(String requestMethod, Optional<Map<String, String>> params) {
        boolean isNamePresent = params.isPresent() && params.get().containsKey("name");
        boolean isNewNamePresent = params.isPresent() && params.get().containsKey("newName");
        switch (requestMethod.toUpperCase()) {
            case "POST":
                if (isNamePresent) {
                    return new HttpResponse(200, userRepository.create(params.get().get("name")));
                } else {
                    return new HttpResponse(404, ErrMsgConstant.PARAMETER_NOT_FOUND);
                }
            case "GET":
                return new HttpResponse(200, userRepository.read());
            case "PUT":
                if (isNamePresent && isNewNamePresent) {
                    return new HttpResponse(200, userRepository.update(params.get().get("name"), params.get().get("newName")));
                } else {
                    return new HttpResponse(404, ErrMsgConstant.PARAMETER_NOT_FOUND);
                }
            case "DELETE":
                if (isNamePresent) {
                    return new HttpResponse(200, userRepository.delete(params.get().get("name")));
                } else {
                    return new HttpResponse(404, ErrMsgConstant.PARAMETER_NOT_FOUND);
                }
            default:
                return new HttpResponse(501, ErrMsgConstant.REQUEST_NOT_IMPLEMENTED);
        }
    }


}

