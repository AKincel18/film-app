package com.filmapp.category;

import com.filmapp.category.exception.CannotAddCategoryException;
import com.filmapp.category.exception.CategoryNotExistException;
import com.filmapp.category.payload.CreateCategoryRequest;
import com.filmapp.category.payload.UpdateCategoryRequest;
import com.filmapp.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    //@PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_MODERATOR'})")
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @PostMapping
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> create(@RequestBody @Valid CreateCategoryRequest request) {
        CategoryDto createdCategory;
        try {
            createdCategory = categoryService.save(request);
        } catch (CannotAddCategoryException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.created(URI.create("/" + createdCategory.getId())).body(createdCategory);
    }

    @PutMapping
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCategoryRequest request) {
        CategoryDto updatedCategory;
        try {
            updatedCategory = categoryService.update(request);
        } catch (CannotAddCategoryException | CategoryNotExistException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.created(URI.create("/" + updatedCategory.getId())).body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<List<CategoryDto>> delete(@PathVariable Long id) {
        boolean isDeleted = categoryService.delete(id);
        return isDeleted ? ResponseEntity.ok().body(categoryService.getAll()) : ResponseEntity.notFound().build();
    }
}
