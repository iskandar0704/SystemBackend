package com.company.payload.requests;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SetPasswordDTO {
    private String username;
    private String password;
    private String prePassword;
}
