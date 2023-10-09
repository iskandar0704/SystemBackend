package com.company.utils;

import com.company.payload.requests.UserDTO;
import com.company.payload.responses.VerificatedUserDTO;
import io.jsonwebtoken.*;

import java.util.Date;

public class JwtUtil {
    private static final String verificationToken = "VERIFICATION_TOKEN";
    private static final String accessToken = "ACCESS_TOKEN";
    private static final String refreshToken = "REFRESH_TOKEN";

    private static final int verificationLiveTime = 1000 * 3600 * 1; // 1 hours
    private static final int accessTokenLiveTime = 1000 * 3600 * 12; // 12 hours
    private static final int refreshTokenLiveTime = 1000 * 3600 * 24; // 1-day

    public static String encodeVerificationToken(String email) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, verificationToken);

        jwtBuilder.claim("email", email);

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (verificationLiveTime)));
        jwtBuilder.setIssuer("System backend");
        return jwtBuilder.compact();
    }


    public static String encodeAccessToken(String username, String password) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, accessToken);

        jwtBuilder.claim("username", username);
        jwtBuilder.claim("password", password);

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (accessTokenLiveTime)));
        jwtBuilder.setIssuer("System backend");
        return jwtBuilder.compact();
    }

    public static String encodeRefreshToken(String username, String password) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, refreshToken);

        jwtBuilder.claim("username", username);
        jwtBuilder.claim("password", password);

        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (refreshTokenLiveTime)));
        jwtBuilder.setIssuer("System backend");
        return jwtBuilder.compact();
    }


    public static VerificatedUserDTO decodeVerificationToken(String verificationToken) {
        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(accessToken);

        Jws<Claims> jws = jwtParser.parseClaimsJws(verificationToken);

        Claims claims = jws.getBody();

        String email = (String) claims.get("email");

        return new VerificatedUserDTO(email);
    }


    public static UserDTO decodeAccessToken(String token) {
        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(accessToken);

        Jws<Claims> jws = jwtParser.parseClaimsJws(token);

        Claims claims = jws.getBody();

        String username = (String) claims.get("username");
        String password = (String) claims.get("password");

        return new UserDTO(username, password);
    }

    public static UserDTO decodeRefreshToken(String token) {
        JwtParser jwtParser = Jwts.parser();
        jwtParser.setSigningKey(refreshToken);

        Jws<Claims> jws = jwtParser.parseClaimsJws(token);

        Claims claims = jws.getBody();

        String username = (String) claims.get("username");
        String password = (String) claims.get("password");

        return new UserDTO(username, password);
    }
}
