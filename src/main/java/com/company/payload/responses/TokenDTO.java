package com.company.payload.responses;

import com.company.utils.AppConstants;
import lombok.Getter;

@Getter
public class TokenDTO {
    private final String tokenType = AppConstants.TOKEN_TYPE;

    private final String accessToken;

    private final String refreshToken;

    public TokenDTO(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
