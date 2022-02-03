package com.filmapp.role.person.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UpdatePersonRoleRequest {
    @NotNull(message = "Id is not provided")
    private Long id;

    @NotBlank(message = "Name must be not blank")
    private String name;
}
