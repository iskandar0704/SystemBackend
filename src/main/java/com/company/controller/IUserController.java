package com.company.controller;

import com.company.payload.requests.SetPasswordDTO;
import com.company.payload.requests.SetUsernameDTO;
import com.company.payload.responses.ApiResult;
import com.company.utils.AppConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = IUserController.BASE_PATH)
public interface IUserController {
    String BASE_PATH = AppConstants.BASE_PATH +"/user";
    String SET_USERNAME = "/set-username";
    String SET_PASSWORD = "/set-password";

    @PostMapping(value = SET_USERNAME)
    ApiResult<?> setUsername(@RequestBody SetUsernameDTO usernameHandlerDTO);

    @PostMapping(value = SET_PASSWORD)
    ApiResult<?> setPassword(@RequestBody SetPasswordDTO setPasswordDTO);

    @GetMapping("/he")
    ApiResult<?> hello();
}
