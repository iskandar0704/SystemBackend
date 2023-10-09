package com.company.entity;

import com.company.enums.UserRole;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class User {
    private UUID id;
    private String email;
    private String username;
    private String password;
    private UserRole role;
    private LocalDateTime createdDate;

}
