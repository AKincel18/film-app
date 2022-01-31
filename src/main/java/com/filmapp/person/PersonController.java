package com.filmapp.person;

import com.filmapp.commons.exception.NotExistException;
import com.filmapp.exception.PersonRoleNotExistException;
import com.filmapp.person.exception.PersonNotExistsException;
import com.filmapp.person.payload.CreatePersonRequest;
import com.filmapp.person.payload.UpdatePersonRequest;
import com.filmapp.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    ResponseEntity<?> createPerson(@RequestBody @Valid CreatePersonRequest request) {
        PersonDto savedPersonDto;
        try {
            savedPersonDto = personService.createPerson(request);
        } catch (PersonRoleNotExistException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.created(URI.create("/" + savedPersonDto.getId())).body(savedPersonDto);
    }

    @PatchMapping
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    ResponseEntity<?> updatePerson(@RequestBody UpdatePersonRequest request) {
        PersonDto updatedPerson;
        try {
            updatedPerson = personService.updatePerson(request);
        } catch (NotExistException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.ok(updatedPerson);
    }

    @GetMapping
    ResponseEntity<?> getAllPersons() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getPersonById(@PathVariable Long id) {
        PersonDto personDto;
        try {
            personDto = personService.findPersonDtoById(id);
        } catch (PersonNotExistsException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.ok(personDto);
    }

    @GetMapping("/role/{id}")
    ResponseEntity<?> getByRole(@PathVariable Long id) {
        List<PersonDto> persons;
        try {
            persons = personService.findPersonsByRoleId(id);
        } catch (PersonRoleNotExistException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        if (persons != null)
            return ResponseEntity.ok(persons);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePerson(@PathVariable Long id) {
        try {
            personService.deletePerson(id);
        } catch (PersonNotExistsException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return getAllPersons();
    }
}
