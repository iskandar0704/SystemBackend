package com.company.payload.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class JwtDTO {
    private String username;
    private String password;
}
