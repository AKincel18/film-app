package com.filmapp.role.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRoleRepository extends JpaRepository<PersonRole, Long> {
    boolean existsByName(PersonRoleEnum name);
}
