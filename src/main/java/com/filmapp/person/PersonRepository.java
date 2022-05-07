package com.filmapp.person;

import com.filmapp.role.person.PersonRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findPeopleByPersonRole(PersonRole role);

    @Query(nativeQuery = true, value =
            "select p.* from persons p " +
                    "inner join person_roles r on p.person_role_id = r.id " +
                    "where r.name = 'Director' " +
                    "order by p.last_name, p.first_name")
    List<Person> findAllDirectors();
}
