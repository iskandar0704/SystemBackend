package com.company.controller;

import com.company.payload.requests.SignInDTO;
import com.company.payload.requests.SignUpDTO;
import com.company.payload.responses.ApiResult;
import com.company.service.IAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController implements IAuthController{

    private final IAuthService authService;

    @Override
    public ApiResult<?> signIn(SignInDTO signInDTO) {
        return authService.signIn(signInDTO);
    }

    @Override
    public ApiResult<?> signUp(SignUpDTO signUpDTO) {
        return authService.signUp(signUpDTO);
    }

    @Override
    public ApiResult<?> verification(String verificationToken) {
        return authService.verification(verificationToken);
    }

    @Override
    public ApiResult<?> hello() {
        return ApiResult.successResponse("Hello");
    }
}
