package com.filmapp.role.person;


import com.filmapp.role.person.exception.CannotAddPersonRoleException;
import com.filmapp.role.person.exception.PersonRoleNotExistException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonRoleService {

    private final PersonRoleRepository personRoleRepository;
    private final ModelMapper mapper;

    public PersonRoleService(PersonRoleRepository personRoleRepository) {
        this.personRoleRepository = personRoleRepository;
        this.mapper = new ModelMapper();
    }

    public List<PersonRoleDto> getAll() {
        return personRoleRepository.findAll()
                .stream()
                .map(c -> mapper.map(c, PersonRoleDto.class))
                .collect(Collectors.toList());
    }

    public PersonRoleDto save(PersonRoleDto personRoleToCreate) throws CannotAddPersonRoleException {
        PersonRole personRole = mapper.map(personRoleToCreate, PersonRole.class);
        if (personRole == null || personRole.getName() == null) {
            throw new CannotAddPersonRoleException();
        }
        PersonRole savedPersonRole = personRoleRepository.save(personRole);
        return mapper.map(savedPersonRole, PersonRoleDto.class);
    }

    public PersonRoleDto update(PersonRoleDto personRoleToUpdate) throws CannotAddPersonRoleException, PersonRoleNotExistException {
        if (personRoleToUpdate == null)
            throw new CannotAddPersonRoleException();
        PersonRole personRole = findPersonRoleById(personRoleToUpdate.getId());
        return save(mapper.map(personRole, PersonRoleDto.class));
    }

    public boolean delete(Long id) {
        Optional<PersonRole> personRole = personRoleRepository.findById(id);
        if (personRole.isEmpty())
            return false;

        personRoleRepository.delete(personRole.get());
        return true;
    }
    
    public PersonRole findPersonRoleById(Long id) throws PersonRoleNotExistException {
        Optional<PersonRole> personRole = personRoleRepository.findById(id);
        if (personRole.isPresent())
            return personRole.get();
        throw new PersonRoleNotExistException();
    }
}
