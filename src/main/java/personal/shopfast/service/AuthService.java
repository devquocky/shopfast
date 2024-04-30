package personal.shopfast.service;

import personal.shopfast.dto.request.LoginRequest;
import personal.shopfast.dto.request.SignupRequest;
import personal.shopfast.dto.request.TokenRefreshRequest;
import personal.shopfast.dto.response.JwtResponse;
import personal.shopfast.dto.response.MessageResponse;
import personal.shopfast.dto.response.TokenRefreshResponse;

public interface AuthService {

    JwtResponse authenticateUser(LoginRequest loginRequest);

    TokenRefreshResponse refreshToken(TokenRefreshRequest request);

    MessageResponse registerUser(SignupRequest signUpRequest);
}
