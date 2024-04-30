package personal.shopfast.service;

import personal.shopfast.dto.request.LoginRequest;
import personal.shopfast.dto.request.SignupRequest;
import personal.shopfast.dto.response.JwtResponse;
import personal.shopfast.dto.response.MessageResponse;

public interface AuthService {

    JwtResponse authenticateUser(LoginRequest loginRequest);

    MessageResponse registerUser(SignupRequest signUpRequest);
}
