package com.company.security;

import com.company.entity.User;
import com.company.payload.requests.UserDTO;
import com.company.repository.IUserRepository;
import com.company.utils.AppConstants;
import com.company.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader(AppConstants.AUTH_HEADER);

        if(Objects.nonNull(authorization)){
            if(authorization.startsWith(AppConstants.TOKEN_TYPE)){
                UserDTO userDTO = JwtUtil
                        .decodeAccessToken(authorization.substring(AppConstants.TOKEN_TYPE.length()));

                User user = userRepository.findByUsername(userDTO.getUsername());

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        new ArrayList<>()));
            }
        }
        filterChain.doFilter(request, response);
    }



}
