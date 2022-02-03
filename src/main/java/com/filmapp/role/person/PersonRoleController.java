package com.filmapp.role.person;

import com.filmapp.response.MessageResponse;
import com.filmapp.role.person.exception.CannotAddPersonRoleException;
import com.filmapp.role.person.exception.PersonRoleNotExistException;
import com.filmapp.role.person.payload.CreatePersonRoleRequest;
import com.filmapp.role.person.payload.UpdatePersonRoleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/person-roles")
public class PersonRoleController {

    private final PersonRoleService personRoleService;

    public PersonRoleController(PersonRoleService personRoleService) {
        this.personRoleService = personRoleService;
    }

    @GetMapping
    //@PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_MODERATOR'})")
    public ResponseEntity<List<PersonRoleDto>> getAll() {
        return ResponseEntity.ok(personRoleService.getAll());
    }

    @PostMapping
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> create(@RequestBody @Valid CreatePersonRoleRequest request) {
        PersonRoleDto createdPersonRole;
        try {
            createdPersonRole = personRoleService.save(request);
        } catch (CannotAddPersonRoleException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.created(URI.create("/" + createdPersonRole.getId())).body(createdPersonRole);
    }

    @PutMapping
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> update(@RequestBody @Valid UpdatePersonRoleRequest request) {
        PersonRoleDto updatedPersonRole;
        try {
            updatedPersonRole = personRoleService.update(request);
        } catch (CannotAddPersonRoleException | PersonRoleNotExistException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.created(URI.create("/" + updatedPersonRole.getId())).body(updatedPersonRole);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<List<PersonRoleDto>> delete(@PathVariable Long id) {
        boolean isDeleted = personRoleService.delete(id);
        return isDeleted ? ResponseEntity.ok().body(personRoleService.getAll()) : ResponseEntity.notFound().build();
    }
}
