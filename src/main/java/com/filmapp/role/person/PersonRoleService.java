package com.filmapp.role.person;


import com.filmapp.dictionary.DictionaryService;
import org.springframework.stereotype.Service;

@Service
public class PersonRoleService extends DictionaryService<PersonRole> {

    public PersonRoleService(PersonRoleRepository personRoleRepository) {
        super(personRoleRepository);
    }
}
