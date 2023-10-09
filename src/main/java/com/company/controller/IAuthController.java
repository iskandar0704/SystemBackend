package com.company.controller;

import com.company.payload.requests.SignInDTO;
import com.company.payload.requests.SignUpDTO;
import com.company.payload.responses.ApiResult;
import com.company.utils.AppConstants;
import org.springframework.web.bind.annotation.*;

@RequestMapping(IAuthController.BASE_PATH)
public interface IAuthController {
    String BASE_PATH = AppConstants.BASE_PATH +"/auth";
    String SIGN_IN_PATH = "/sign-in";

    String SIGN_UP_PATH = "/sign-up";

    String VERIFICATION = "/verification";

    String HELLO_PATH = "/hello";

    @PostMapping(value = SIGN_IN_PATH)
    ApiResult<?> signIn(@RequestBody SignInDTO signInDTO);

    @PostMapping(value = SIGN_UP_PATH)
    ApiResult<?> signUp(@RequestBody SignUpDTO signUpDTO);

    @PostMapping(value = VERIFICATION)
    ApiResult<?> verification(@PathVariable("verificationToken") String verificationToken);



    @GetMapping(value = HELLO_PATH)
    ApiResult<?> hello();
}
