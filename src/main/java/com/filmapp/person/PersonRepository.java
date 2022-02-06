package com.filmapp.person;

import com.filmapp.role.person.PersonRole;
import com.filmapp.role.person.PersonRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findPeopleByPersonRole(PersonRole role);
    Optional<Person> findPersonByIdAndPersonRole_Name(Long id, PersonRoleEnum role);
}
