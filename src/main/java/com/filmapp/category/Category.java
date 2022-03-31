package com.filmapp.category;

import com.filmapp.dictionary.Dictionary;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(name = "categories")
public class Category extends Dictionary {
}
