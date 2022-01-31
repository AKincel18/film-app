package com.filmapp.person;

import com.filmapp.commons.exception.NotExistException;
import com.filmapp.exception.PersonRoleNotExistException;
import com.filmapp.person.exception.PersonNotExistsException;
import com.filmapp.person.payload.CreatePersonRequest;
import com.filmapp.person.payload.UpdatePersonRequest;
import com.filmapp.role.person.PersonRole;
import com.filmapp.role.person.PersonRoleEnum;
import com.filmapp.role.person.PersonRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonRoleService personRoleService;
    private final ModelMapper mapper;

    public PersonService(PersonRepository personRepository, PersonRoleService personRoleService) {
        this.personRepository = personRepository;
        this.personRoleService = personRoleService;
        this.mapper = new ModelMapper();
    }

    public PersonDto createPerson(CreatePersonRequest request) throws PersonRoleNotExistException {
        PersonRole personRole = personRoleService.findPersonRoleById(request.getRoleId());
        Person person = mapper.map(request, Person.class);
        person.setRole(personRole);
        Person savedPerson = personRepository.save(person);
        return mapper.map(savedPerson, PersonDto.class);
    }

    public PersonDto updatePerson(UpdatePersonRequest request) throws NotExistException {
        if (request.getId() == null)
            throw new PersonNotExistsException();
        Optional<Person> personOptional = personRepository.findById(request.getId());
        if (personOptional.isEmpty())
            throw new PersonNotExistsException();
        Person person = personOptional.get();
        if (request.getFirstName() != null) {
            person.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            person.setLastName(request.getLastName());
        }
        if (request.getBirthDate() != null) {
            person.setBirthDate(request.getBirthDate());
        }
        if (request.getRoleId() != null) {
            PersonRole personRole = personRoleService.findPersonRoleById(request.getRoleId());
            person.setRole(personRole);
        }

        return mapper.map(personRepository.save(person), PersonDto.class);
    }

    public PersonDto findPersonDtoById(Long id) throws PersonNotExistsException {
        return mapper.map(findPersonById(id), PersonDto.class);
    }

    public Person findPersonById(Long id) throws PersonNotExistsException{
        Optional<Person> person = personRepository.findById(id);
        if (person.isEmpty()) {
            throw new PersonNotExistsException();
        }
        return person.get();
    }

    public List<PersonDto> findPersonsByRoleId(Long roleId) throws PersonRoleNotExistException {
        PersonRole personRole = personRoleService.findPersonRoleById(roleId);
        return personRepository.findPeopleByRole(personRole)
                .stream()
                .map(p -> mapper.map(p, PersonDto.class))
                .collect(Collectors.toList());
    }

    public Person findDirectorById(Long id) throws PersonNotExistsException {
        Optional<Person> person = personRepository.findPersonByIdAndRole_Role(id, PersonRoleEnum.DIRECTOR);
        if (person.isEmpty())
            throw new PersonNotExistsException();
        return person.get();
    }

    public List<PersonDto> getAllPersons() {
        return personRepository.findAll()
                .stream()
                .map(p -> mapper.map(p, PersonDto.class))
                .collect(Collectors.toList());
    }

    public void deletePerson(Long id) throws PersonNotExistsException {
        Optional<Person> person = personRepository.findById(id);
        if (person.isEmpty()) {
            throw new PersonNotExistsException();
        }
        personRepository.delete(person.get());
    }
}
