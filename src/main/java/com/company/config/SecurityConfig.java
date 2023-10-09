package com.company.config;

import com.company.controller.IAuthController;
import com.company.controller.IUserController;
import com.company.controller.IWorkerController;
import com.company.security.AuthEntryPoint;
import com.company.security.JwtFilter;
import com.company.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final AuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests( (auth) ->
                        auth
                                .requestMatchers(IWorkerController.BASE_PATH+"/**",IUserController.BASE_PATH+"/**")
                                .authenticated()
                )
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(AppConstants.BASE_PATH+"/**")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(IAuthController.VERIFICATION)
                .permitAll()
                .and()
                .build();
    }
}
