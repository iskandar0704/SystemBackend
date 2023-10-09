package com.company.service;

import com.company.payload.requests.SetPasswordDTO;
import com.company.payload.requests.SetUsernameDTO;
import com.company.payload.responses.ApiResult;
import com.company.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final IUserRepository userRepository;

    @Override
    public ApiResult<?> setUsername(SetUsernameDTO setUsernameDTO) {
        if(setUsernameDTO.getUsername() == null || setUsernameDTO.getEmail() == null){
            return ApiResult.failResponse("Empty username or email!",400);
        }

        if(!userRepository.existsByEmail(setUsernameDTO.getEmail())){
            return ApiResult.failResponse("User with this email does not exists!",400);
        }

        if(userRepository.existsByUsername(setUsernameDTO.getUsername())){
            return ApiResult.failResponse("Username already used! Choose another username",400);
        }

        if (!userRepository.setUsername(setUsernameDTO)){
            return ApiResult.failResponse("Something went wrong on set!",400);
        }

        return ApiResult.successResponse("Username set successfully!");
    }

    @Override
    public ApiResult<?> setPassword(SetPasswordDTO setPasswordDTO) {
        if(setPasswordDTO.getPassword() == null
        || setPasswordDTO.getPrePassword() == null || setPasswordDTO.getUsername() == null){
            return ApiResult.failResponse("Empty password or pre-password or email!",400);
        }

        if(!setPasswordDTO.getPassword().equals(setPasswordDTO.getPrePassword())){
            return ApiResult.failResponse("Password not equal with pre-password!",400);
        }

        if(!userRepository.existsByUsername(setPasswordDTO.getUsername())){
            return ApiResult.failResponse("User with this username does not exists!",400);
        }

        if(!userRepository.setPassword(setPasswordDTO)){
            return ApiResult.failResponse("Something went wrong on set password!",400);
        }

        return ApiResult.successResponse("Password set successfully!");
    }
}
