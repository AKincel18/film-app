package com.filmapp.security.services;

import com.filmapp.role.user.UserRole;
import com.filmapp.role.user.UserRoleService;
import com.filmapp.security.exceptions.UserNotSavedException;
import com.filmapp.security.jwt.JwtUtils;
import com.filmapp.security.payload.request.LoginRequest;
import com.filmapp.security.payload.request.SignupRequest;
import com.filmapp.security.payload.response.JwtResponse;
import com.filmapp.user.User;
import com.filmapp.user.UserDto;
import com.filmapp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.filmapp.security.consts.Message.*;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRoleService userRoleService;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);

        UserDto userDto = (UserDto) authentication.getPrincipal();
        String role = userDto.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst().orElse(null);

        return JwtResponse.build(token, role, userDto);
    }

    public void signup(SignupRequest request) throws UserNotSavedException {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserNotSavedException(USERNAME_EXISTS);
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserNotSavedException(EMAIL_EXISTS);
        }
        UserRole userRole = userRoleService.findUserRole(request.getRole());
        if (userRole == null) {
            throw new UserNotSavedException(ROLE_NOT_EXIST);
        }

        User user = new User(request.getUsername(),
                request.getEmail(),
                encoder.encode(request.getPassword()),
                userRole);

        userRepository.save(user);
    }
}
