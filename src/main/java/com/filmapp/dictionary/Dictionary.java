package com.filmapp.dictionary;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DICTIONARIES_GEN_SEQ")
    @SequenceGenerator(name = "DICTIONARIES_GEN_SEQ", sequenceName = "DICTIONARIES_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "name", unique = true)
    @NotBlank(message = "Name must be not blank")
    private String name;

}
