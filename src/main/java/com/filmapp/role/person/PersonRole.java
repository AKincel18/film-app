package com.filmapp.role.person;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "person_roles")
public class PersonRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_ROLES_GEN_SEQ")
    @SequenceGenerator(name = "PERSON_ROLES_GEN_SEQ", sequenceName = "PERSON_ROLES_SEQ", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PersonRoleEnum name;
}
