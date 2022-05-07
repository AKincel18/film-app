package com.filmapp.category;

import com.filmapp.dictionary.Dictionary;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(name = "categories")
public class Category extends Dictionary {
}
