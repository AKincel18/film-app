package com.filmapp.role.user.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UpdateUserRoleRequest {

    @NotNull(message = "Id is not provided")
    private Long id;

    @NotBlank(message = "Name must be not blank")
    private String name;
}
