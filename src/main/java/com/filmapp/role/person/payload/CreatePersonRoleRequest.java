package com.filmapp.role.person.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreatePersonRoleRequest {

    @NotBlank(message = "Name must be not blank")
    private String name;
}
