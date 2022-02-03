package com.filmapp.role.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ROLES_GEN_SEQ")
    @SequenceGenerator(name = "USER_ROLES_GEN_SEQ", sequenceName = "USER_ROLES_SEQ", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRoleEnum name;
}
