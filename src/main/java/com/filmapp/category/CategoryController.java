package com.filmapp.category;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping
    List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping
    ResponseEntity<Category> createCategory(@RequestBody Category categoryToCreate) {
        Category createdCategory = categoryRepository.save(categoryToCreate);
        return ResponseEntity.created(URI.create("/" + createdCategory.getId())).body(createdCategory);
    }

    @PutMapping
    ResponseEntity<Category> updateCategory(@RequestBody Category categoryToUpdate) {
        Category updatedCategory = categoryRepository.save(categoryToUpdate);
        return ResponseEntity.created(URI.create("/" + updatedCategory.getId())).body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<List<Category>> deleteCategory(@PathVariable ObjectId id) {
        Optional<Category> categoryToDelete = categoryRepository.findById(id);
        if (categoryToDelete.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        categoryRepository.delete(categoryToDelete.get());
        return ResponseEntity.ok().body(getAllCategories());

    }
}
