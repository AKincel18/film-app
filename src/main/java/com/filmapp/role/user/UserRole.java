package com.filmapp.role.user;

import com.filmapp.dictionary.Dictionary;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity(name = "user_roles")
public class UserRole extends Dictionary {
}
