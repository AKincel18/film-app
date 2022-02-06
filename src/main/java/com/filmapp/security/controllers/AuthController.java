package com.filmapp.security.controllers;

import com.filmapp.commons.response.MessageResponse;
import com.filmapp.security.exceptions.UserNotSavedException;
import com.filmapp.security.payload.request.LoginRequest;
import com.filmapp.security.payload.request.SignupRequest;
import com.filmapp.security.payload.response.JwtResponse;
import com.filmapp.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.filmapp.security.consts.Message.REGISTERED_SUCCESSFULLY;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final SecurityService securityService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(securityService.login(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        try {
            securityService.signup(signUpRequest);
        } catch (UserNotSavedException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.ok(new MessageResponse(REGISTERED_SUCCESSFULLY));
    }
}
