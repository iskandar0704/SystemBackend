package com.company.service;

import com.company.payload.requests.SignInDTO;
import com.company.payload.requests.SignUpDTO;
import com.company.payload.responses.ApiResult;

public interface IAuthService {
    ApiResult<?> signIn(SignInDTO signInDTO);

    ApiResult<?> signUp(SignUpDTO signUpDTO);

    ApiResult<?> verification(String verificationToken);
}
