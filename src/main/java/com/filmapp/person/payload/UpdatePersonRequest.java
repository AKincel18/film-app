package com.filmapp.person.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UpdatePersonRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Long roleId;
}
