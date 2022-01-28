package com.filmapp.category;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIES_GEN_SEQ")
    @SequenceGenerator(name = "CATEGORIES_GEN_SEQ", sequenceName = "CATEGORIES_SEQ", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoryEnum name;
}
