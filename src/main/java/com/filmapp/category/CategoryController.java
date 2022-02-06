package com.filmapp.category;

import com.filmapp.category.exception.CannotAddCategoryException;
import com.filmapp.category.exception.CategoryNotExistException;
import com.filmapp.category.exception.DuplicatedCategoryException;
import com.filmapp.category.payload.CreateCategoryRequest;
import com.filmapp.category.payload.UpdateCategoryRequest;
import com.filmapp.commons.exception.processing.MyExceptionProcessing;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@MyExceptionProcessing
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
    public ResponseEntity<?> create(@RequestBody @Valid CreateCategoryRequest request)
            throws DuplicatedCategoryException, CannotAddCategoryException {
        CategoryDto createdCategory = categoryService.save(request);
        return ResponseEntity.created(URI.create("/" + createdCategory.getId())).body(createdCategory);
    }

    @PutMapping
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateCategoryRequest request)
            throws CannotAddCategoryException, CategoryNotExistException {
        CategoryDto updatedCategory = categoryService.update(request);
        return ResponseEntity.created(URI.create("/" + updatedCategory.getId())).body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<List<CategoryDto>> delete(@PathVariable Long id) {
        boolean isDeleted = categoryService.delete(id);
        return isDeleted ? ResponseEntity.ok().body(categoryService.getAll()) : ResponseEntity.notFound().build();
    }
}
