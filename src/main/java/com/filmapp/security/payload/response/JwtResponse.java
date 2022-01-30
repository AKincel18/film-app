package com.filmapp.security.payload.response;

import com.filmapp.role.user.UserRoleEnum;
import com.filmapp.user.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private Long id;
    private String username;
    private String email;
    private UserRoleEnum role;

    public JwtResponse(String token, Long id, String username, String email, UserRoleEnum role) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public static JwtResponse build(String token, String role, UserDto userDto) {
        return new JwtResponse(token,
                userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                UserRoleEnum.valueOf(role)
        );
    }
}
