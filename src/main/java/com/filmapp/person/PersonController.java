package com.filmapp.person;

import com.filmapp.commons.exception.NotExistException;
import com.filmapp.commons.exception.processing.MyExceptionProcessing;
import com.filmapp.person.exception.PersonNotExistsException;
import com.filmapp.person.payload.CreatePersonRequest;
import com.filmapp.person.payload.UpdatePersonRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@MyExceptionProcessing
@RequestMapping("api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    ResponseEntity<?> createPerson(@RequestBody @Valid CreatePersonRequest request) throws NotExistException {
        PersonDto savedPersonDto = personService.createPerson(request);
        return ResponseEntity.created(URI.create("/" + savedPersonDto.getId())).body(savedPersonDto);
    }

    @PatchMapping
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    ResponseEntity<?> updatePerson(@RequestBody @Valid UpdatePersonRequest request) throws NotExistException {
        PersonDto updatedPerson = personService.updatePerson(request);
        return ResponseEntity.ok(updatedPerson);
    }

    @GetMapping
    ResponseEntity<?> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getPersonById(@PathVariable Long id) throws PersonNotExistsException {
        PersonDto personDto = personService.findPersonDtoById(id);
        return ResponseEntity.ok(personDto);
    }

    @GetMapping("/role/{id}")
    ResponseEntity<?> getByRole(@PathVariable Long id) throws NotExistException {
        List<PersonDto> persons = personService.findPersonsByRoleId(id);
        return ResponseEntity.ok(persons);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePerson(@PathVariable Long id) throws PersonNotExistsException {
        personService.deletePerson(id);
        return getAllPersons();
    }
}
