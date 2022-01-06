package com.filmapp.security.controllers;

import com.filmapp.role.user.UserRole;
import com.filmapp.role.user.UserRoleEnum;
import com.filmapp.role.user.UserRoleService;
import com.filmapp.security.jwt.JwtUtils;
import com.filmapp.security.payload.request.LoginRequest;
import com.filmapp.security.payload.request.SignupRequest;
import com.filmapp.security.payload.response.JwtResponse;
import com.filmapp.security.payload.response.MessageResponse;
import com.filmapp.user.User;
import com.filmapp.user.UserDto;
import com.filmapp.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.filmapp.security.consts.Message.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDto userDto = (UserDto) authentication.getPrincipal();
        String role = userDto.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst().orElse(null);

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                UserRoleEnum.valueOf(role)
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(USERNAME_EXISTS));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(EMAIL_EXISTS));
        }
        UserRole userRole = userRoleService.findUserRole(signUpRequest.getRole());
        if (userRole == null) {
            ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(ROLE_NOT_EXIST));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                userRole);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse(REGISTERED_SUCCESSFULLY));
    }
}
