package com.company.payload.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class SignUpResponseDTO {
    private String email;
    private String username;
    private String password;
}
