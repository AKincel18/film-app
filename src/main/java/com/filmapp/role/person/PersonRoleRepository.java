package com.filmapp.role.person;

import com.filmapp.dictionary.DictionaryRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRoleRepository extends DictionaryRepository<PersonRole> {
    PersonRole findPersonRoleByNameEquals(String director);
}
