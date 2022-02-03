package com.filmapp.role.user;

import com.filmapp.response.MessageResponse;
import com.filmapp.role.user.excpetion.CannotAddUserRoleException;
import com.filmapp.role.user.payload.CreateUserRoleRequest;
import com.filmapp.role.user.payload.UpdateUserRoleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/user-roles")
public class UserRoleController {
    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    //@PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_MODERATOR'})")
    public ResponseEntity<List<UserRoleDto>> getAll() {
        return ResponseEntity.ok(userRoleService.getAll());
    }

    @PostMapping
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> create(@RequestBody @Valid CreateUserRoleRequest request) {
        UserRoleDto createdUserRole;
        try {
            createdUserRole = userRoleService.save(request);
        } catch (CannotAddUserRoleException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.created(URI.create("/" + createdUserRole.getId())).body(createdUserRole);
    }

    @PutMapping
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateUserRoleRequest request) {
        UserRoleDto updatedUserRole;
        try {
            updatedUserRole = userRoleService.update(request);
        } catch (CannotAddUserRoleException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.created(URI.create("/" + updatedUserRole.getId())).body(updatedUserRole);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<List<UserRoleDto>> delete(@PathVariable Long id) {
        boolean isDeleted = userRoleService.delete(id);
        return isDeleted ? ResponseEntity.ok().body(userRoleService.getAll()) : ResponseEntity.notFound().build();
    }
}
