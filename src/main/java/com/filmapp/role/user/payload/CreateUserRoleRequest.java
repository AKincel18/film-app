package com.filmapp.role.user.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateUserRoleRequest {

    @NotBlank(message = "Name must be not blank")
    private String name;
}
