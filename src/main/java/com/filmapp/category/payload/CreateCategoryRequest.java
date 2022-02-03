package com.filmapp.category.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CreateCategoryRequest {

    @NotBlank(message = "Name must be not blank")
    private String name;
}
