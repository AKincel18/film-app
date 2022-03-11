package com.filmapp.role.person;

import com.filmapp.dictionary.Dictionary;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(name = "person_roles")
public class PersonRole extends Dictionary {
}
