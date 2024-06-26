package personal.shopfast.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.shopfast.dao.entity.ERole;
import personal.shopfast.dao.entity.Role;
import personal.shopfast.dao.entity.User;
import personal.shopfast.dao.repository.RoleRepository;
import personal.shopfast.dao.repository.UserRepository;
import personal.shopfast.dto.request.LoginRequest;
import personal.shopfast.dto.request.SignupRequest;
import personal.shopfast.dto.response.JwtResponse;
import personal.shopfast.dto.response.MessageResponse;
import personal.shopfast.exception.ResourceNotFoundException;
import personal.shopfast.security.JwtUtils;
import personal.shopfast.service.AuthService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    @Transactional
    public JwtResponse authenticateUser(LoginRequest loginRequest) {

        // Get authentication information based on username/password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        // Set SecurityContextHolder and generate JWT
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        // Get user detail from authentication object
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    @Override
    @Transactional
    public MessageResponse registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new MessageResponse("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<Long> roleIds = signUpRequest.getRoleIds();
        List<Role> foundRoles = roleRepository.findAllById(roleIds);
        Set<Role> roles = new HashSet<>(foundRoles);

        if (CollectionUtils.isEmpty(roles)) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));
            roles.add(userRole);
        }

        user.setRoles(roles);
        User newUser = userRepository.save(user);

        return new MessageResponse("User registered successfully!", newUser);
    }

}
