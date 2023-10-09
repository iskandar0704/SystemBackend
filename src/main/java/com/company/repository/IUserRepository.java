package com.company.repository;

import com.company.entity.User;
import com.company.payload.requests.SetPasswordDTO;
import com.company.payload.requests.SetUsernameDTO;
import com.company.payload.requests.SignInDTO;


public interface IUserRepository {
    boolean existsByUsername(String username);

    boolean setUsername(SetUsernameDTO usernameHandlerDTO);

    boolean existsByEmail(String email);

    boolean setPassword(SetPasswordDTO setPasswordDTO);

    boolean checkUsernameAndPassword(SignInDTO signInDTO);

    User findByUsername(String username);

    boolean saveNewUser(User user);
}
