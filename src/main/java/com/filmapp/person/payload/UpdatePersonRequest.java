package com.filmapp.person.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UpdatePersonRequest {

    @NotNull(message = "Person role must be given")
    private Long id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Long roleId;
}
