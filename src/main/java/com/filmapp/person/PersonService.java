package com.filmapp.person;

import com.filmapp.exception.PersonRoleNotExistException;
import com.filmapp.role.person.PersonRole;
import com.filmapp.role.person.PersonRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonRoleRepository personRoleRepository;
    private final PersonMapper mapper;

    public PersonService(PersonRepository personRepository, PersonRoleRepository personRoleRepository) {
        this.personRepository = personRepository;
        this.personRoleRepository = personRoleRepository;
        this.mapper = new PersonMapper();
    }

    public PersonDto createPerson(PersonDto personDto) throws PersonRoleNotExistException {
        PersonRole personRole = null;//personRoleRepository.getByName(personDto.getRole());
        if (personRole == null)
            throw new PersonRoleNotExistException();
        Person savedPerson = personRepository.save(mapper.map(personRole, personDto, Person.class));
        return mapper.map(savedPerson, PersonDto.class);
    }

    public PersonDto findPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        return person.map(p -> mapper.map(p, PersonDto.class)).orElse(null);
    }

    public List<PersonDto> findPersonsByRoleId(Long roleId) {
        Optional<PersonRole> personRole = personRoleRepository.findById(roleId);
        return personRole.map(role -> personRepository.findPeopleByRole(role)
                        .stream()
                        .map(p -> mapper.map(p, PersonDto.class))
                        .collect(Collectors.toList()))
                .orElse(null);
    }
}
