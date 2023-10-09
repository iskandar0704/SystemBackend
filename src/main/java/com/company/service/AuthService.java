package com.company.service;

import com.company.db.DataBase;
import com.company.entity.User;
import com.company.payload.requests.SignInDTO;
import com.company.payload.requests.SignUpDTO;
import com.company.payload.requests.UserDTO;
import com.company.payload.responses.ApiResult;
import com.company.payload.responses.SignUpResponseDTO;
import com.company.payload.responses.TokenDTO;
import com.company.payload.responses.VerificatedUserDTO;
import com.company.repository.IUserRepository;
import com.company.service.mail.IMailService;
import com.company.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService{
    private final IUserRepository userRepository;
    private final IMailService mailService;

    @Override
    public ApiResult<?> signIn(SignInDTO signInDTO) {
        if(signInDTO.getUsername().isBlank() || signInDTO.getPassword().isBlank()){
            return ApiResult.failResponse("Username or password empty!",400);
        }

        if(!userRepository.checkUsernameAndPassword(signInDTO)){
            return ApiResult.failResponse("Username or password is wrong!" ,400);
        }

        String accessToken = JwtUtil.encodeAccessToken(signInDTO.getUsername(),signInDTO.getPassword());
        String refreshToken = JwtUtil.encodeRefreshToken(signInDTO.getUsername(),signInDTO.getPassword());

        return ApiResult.successResponse(new TokenDTO(accessToken,refreshToken));
    }

    @Override
    public ApiResult<?> signUp(SignUpDTO signUpDTO) {
        if(userRepository.existsByEmail(signUpDTO.getEmail())){
            return ApiResult.failResponse("User with this email already exists!",401);
        }

        User user = new User();
        user.setEmail(signUpDTO.getEmail());
        user.setUsername(signUpDTO.getEmail());
        user.setPassword(signUpDTO.getEmail());

        boolean result = userRepository.saveNewUser(user);

        if(!result){
            return ApiResult.failResponse("Somethign went wrong!",401);
        }

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                sb.append("<h1 style=\"text-align: center; background-color: indianred; color: white\"> Salom Mazgi qalaysan</h1>");
                String link = String.format("<a href=\"http://localhost:8080/api/v1/auth/verification/%s\"> Click there</a>", JwtUtil.encodeVerificationToken(user.getEmail()));
                sb.append(link);
                mailService.sendEmailMine(user.getEmail(), "Complete Registration", sb.toString());
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        return ApiResult.successResponse("Confirm your email!");
    }

    @Override
    public ApiResult<?> verification(String verificationToken) {

        VerificatedUserDTO verificatedUserDTO = JwtUtil.decodeVerificationToken(verificationToken);

        User user = userRepository.findByUsername(verificatedUserDTO.getEmail());

        if(user == null){
            return ApiResult.failResponse("Something went wrong!",403);
        }

        return ApiResult.successResponse(new TokenDTO(
                JwtUtil.encodeAccessToken(user.getUsername(),user.getPassword()),
                JwtUtil.encodeRefreshToken(user.getUsername(),user.getPassword())));
    }
}
