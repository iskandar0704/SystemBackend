package com.company.utils;



public interface AppConstants {

    String BASE_PATH = "/api/v1";
    String TOKEN_TYPE = "Bearer ";

    String AUTH_HEADER = "Authorization";


    String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-resources",
            "/swagger-resources/**"
    };
}
