package com.filmapp.person;

import com.filmapp.role.person.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findPeopleByPersonRole(PersonRole role);
    List<Person> findPeopleByPersonRoleOrderByLastNameAscFirstName(PersonRole personRole);
}
