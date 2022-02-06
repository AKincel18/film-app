package com.filmapp.role.person;


import com.filmapp.role.person.exception.CannotAddPersonRoleException;
import com.filmapp.role.person.exception.DuplicatedPersonRoleException;
import com.filmapp.role.person.exception.PersonRoleNotExistException;
import com.filmapp.role.person.payload.CreatePersonRoleRequest;
import com.filmapp.role.person.payload.UpdatePersonRoleRequest;
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

    public PersonRoleDto save(CreatePersonRoleRequest request) throws CannotAddPersonRoleException, DuplicatedPersonRoleException {
        PersonRoleEnum personRoleEnum = PersonRoleEnum.findPersonRoleEnum(request.getName());
        if (personRoleRepository.existsByName(personRoleEnum)) {
            throw new DuplicatedPersonRoleException();
        }
        request.setName(personRoleEnum.name());
        PersonRole personRole = mapper.map(request, PersonRole.class);
        if (personRole == null || personRole.getName() == null) {
            throw new CannotAddPersonRoleException();
        }
        PersonRole savedPersonRole = personRoleRepository.save(personRole);
        return mapper.map(savedPersonRole, PersonRoleDto.class);
    }

    public PersonRoleDto update(UpdatePersonRoleRequest request) throws CannotAddPersonRoleException, PersonRoleNotExistException {
        if (request == null)
            throw new CannotAddPersonRoleException();
        PersonRole personRole = findPersonRoleById(request.getId());
        try {
            personRole.setName(PersonRoleEnum.valueOf(request.getName()));
        } catch (IllegalArgumentException e) {
            throw new CannotAddPersonRoleException();
        }
        return mapper.map(personRoleRepository.save(personRole), PersonRoleDto.class);
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
