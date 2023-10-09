package com.company.payload.requests;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SetUsernameDTO {
    private String email;
    private String username;
}
