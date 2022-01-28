package com.filmapp.security.payload.response;

import com.filmapp.role.user.UserRoleEnum;
import lombok.Data;

@Data
public class JwtResponse {
    private final String token;
    private final Long id;
    private final String username;
    private final String email;
    private final UserRoleEnum role;
}
