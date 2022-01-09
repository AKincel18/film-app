package com.filmapp.generic;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

public class GenericControllerImpl<Entity extends BaseEntity> implements GenericController<Entity> {

    @Autowired
    private GenericService<Entity> genericService;

    @GetMapping
    public List<Entity> getAll() {
        return genericService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<Entity> create(@RequestBody Entity entityToCreate) {
        Entity entityCreated = genericService.save(entityToCreate);
        return ResponseEntity.created(URI.create("/" + entityCreated.getId())).body(entityCreated);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<Entity> update(@RequestBody Entity entityToUpdate) {
        Entity updatedEntity = genericService.update(entityToUpdate);
        if (updatedEntity == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.created(URI.create("/" + updatedEntity.getId())).body(updatedEntity);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<List<Entity>> delete(@PathVariable ObjectId id) {
        boolean isDeleted = genericService.delete(id);
        return isDeleted ? ResponseEntity.ok().body(getAll()) : ResponseEntity.notFound().build();
    }
}
