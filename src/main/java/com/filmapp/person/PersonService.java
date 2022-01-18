package com.filmapp.person;

import com.filmapp.exception.PersonRoleNotExistException;
import com.filmapp.generic.GenericRepository;
import com.filmapp.role.person.PersonRole;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final GenericRepository<PersonRole> personRoleRepository;
    private final PersonMapper mapper;

    public PersonService(PersonRepository personRepository, GenericRepository<PersonRole> personRoleRepository) {
        this.personRepository = personRepository;
        this.personRoleRepository = personRoleRepository;
        this.mapper = new PersonMapper();
    }

    public PersonDto createPerson(PersonDto personDto) throws PersonRoleNotExistException {
        PersonRole personRole = personRoleRepository.getByName(personDto.getRole());
        if (personRole == null)
            throw new PersonRoleNotExistException();
        Person savedPerson = personRepository.save(mapper.map(personRole, personDto, Person.class));
        return mapper.map(savedPerson, PersonDto.class);
    }

    public PersonDto findPersonById(ObjectId id) {
        Optional<Person> person = personRepository.findById(id);
        return person.map(p -> mapper.map(p, PersonDto.class)).orElse(null);
    }

    public List<PersonDto> findPersonsByRoleId(ObjectId roleId) {
        Optional<PersonRole> personRole = personRoleRepository.findById(roleId);
        return personRole.map(role -> personRepository.findPeopleByRole(role)
                        .stream()
                        .map(p -> mapper.map(p, PersonDto.class))
                        .collect(Collectors.toList()))
                .orElse(null);
    }
}
