package com.filmapp.category;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_MODERATOR'})")
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryToCreate) {
        CategoryDto createdCategory = categoryService.save(categoryToCreate);
        return ResponseEntity.created(URI.create("/" + createdCategory.getId())).body(createdCategory);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryToUpdate) {
        CategoryDto updatedCategory = categoryService.update(categoryToUpdate);
        if (updatedCategory == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.created(URI.create("/" + updatedCategory.getId())).body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<List<CategoryDto>> delete(@PathVariable Long id) {
        boolean isDeleted = categoryService.delete(id);
        return isDeleted ? ResponseEntity.ok().body(categoryService.getAll()) : ResponseEntity.notFound().build();
    }
}
