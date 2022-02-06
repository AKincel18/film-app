package com.filmapp.person.payload;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreatePersonRequest {

    @NotBlank(message = "First name must be not blank")
    private String firstName;

    @NotBlank(message = "Last name must be not blank")
    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @NotNull(message = "Person role must be given")
    private Long roleId;
}
