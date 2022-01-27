package com.filmapp.person;

import com.filmapp.exception.PersonRoleNotExistException;
import com.filmapp.response.MessageResponse;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<List<PersonDto>> findAllPersons() {
        return ResponseEntity.ok(personService.findAllPersons());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    ResponseEntity<?> createPerson(@RequestBody PersonDto personDto) {
        PersonDto savedPersonDto;
        try {
            savedPersonDto = personService.createPerson(personDto);
        } catch (PersonRoleNotExistException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.created(URI.create("/" + savedPersonDto.getId())).body(savedPersonDto);
    }

    @GetMapping("/{id}")
    ResponseEntity<PersonDto> getPersonById(@PathVariable ObjectId id) {
        PersonDto personDto = personService.findPersonById(id);
        if (personDto != null)
            return ResponseEntity.ok(personDto);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/role/{id}")
    ResponseEntity<List<PersonDto>> getByRole(@PathVariable ObjectId id) {
        List<PersonDto> persons = personService.findPersonsByRoleId(id);
        if (persons != null)
            return ResponseEntity.ok(persons);
        return ResponseEntity.notFound().build();
    }
}
