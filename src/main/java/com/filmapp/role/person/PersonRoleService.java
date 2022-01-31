package com.filmapp.role.person;

import com.filmapp.exception.PersonRoleNotExistException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonRoleService {
    private final PersonRoleRepository personRoleRepository;

    public PersonRoleService(PersonRoleRepository personRoleRepository) {
        this.personRoleRepository = personRoleRepository;
    }

    public PersonRole findPersonRoleById(Long id) throws PersonRoleNotExistException {
        Optional<PersonRole> personRole = personRoleRepository.findById(id);
        if (personRole.isPresent())
            return personRole.get();
        throw new PersonRoleNotExistException();
    }
}
