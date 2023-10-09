package com.company.service;

import com.company.payload.requests.SetPasswordDTO;
import com.company.payload.requests.SetUsernameDTO;
import com.company.payload.responses.ApiResult;

public interface IUserService {
    ApiResult<?> setUsername(SetUsernameDTO usernameHandlerDTO);

    ApiResult<?> setPassword(SetPasswordDTO setPasswordDTO);
}
