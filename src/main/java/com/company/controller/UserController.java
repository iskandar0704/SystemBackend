package com.company.controller;

import com.company.payload.requests.SetPasswordDTO;
import com.company.payload.requests.SetUsernameDTO;
import com.company.payload.responses.ApiResult;
import com.company.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements IUserController{
    private final IUserService userService;

    @Override
    public ApiResult<?> setUsername(SetUsernameDTO usernameHandlerDTO) {
        return userService.setUsername(usernameHandlerDTO);
    }

    @Override
    public ApiResult<?> hello() {
        return ApiResult.successResponse("hello");
    }

    @Override
    public ApiResult<?> setPassword(SetPasswordDTO setPasswordDTO) {
        return userService.setPassword(setPasswordDTO);
    }
}
