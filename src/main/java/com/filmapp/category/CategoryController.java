package com.filmapp.category;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @GetMapping
    List<CategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryToCreate) {
        CategoryDto categoryCreatedDto = categoryService.saveCategory(categoryToCreate);
        return ResponseEntity.created(URI.create("/" + categoryCreatedDto.getId())).body(categoryCreatedDto);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryToUpdate) {
        CategoryDto updatedCategory = categoryService.updateCategory(categoryToUpdate);
        if (updatedCategory == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.created(URI.create("/" + updatedCategory.getId())).body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_MODERATOR')")
    ResponseEntity<List<CategoryDto>> deleteCategory(@PathVariable ObjectId id) {
        Optional<Category> categoryToDelete = categoryRepository.findById(id);
        if (categoryToDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categoryRepository.delete(categoryToDelete.get());
        return ResponseEntity.ok().body(getAllCategories());
    }
}
