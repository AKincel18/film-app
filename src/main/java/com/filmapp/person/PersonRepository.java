package com.filmapp.person;

import com.filmapp.role.person.PersonRole;
import com.filmapp.role.person.PersonRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findPeopleByRole(PersonRole role);
    Optional<Person> findPersonByIdAndRole_Role(Long id, PersonRoleEnum role);
}
