package com.filmapp.category;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper = new ModelMapper();

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(c -> mapper.map(c, CategoryDto.class))
                .collect(Collectors.toList());
    }

    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category savedCategory = categoryRepository.save(mapper.map(categoryDto, Category.class));
        return mapper.map(savedCategory, CategoryDto.class);
    }

    public CategoryDto updateCategory(CategoryDto categoryDto) {
        if (categoryDto == null)
            return null;
        Optional<Category> categoryToUpdate = categoryRepository.findById(categoryDto.getId());
        if (categoryToUpdate.isEmpty())
            return null;
        return saveCategory(categoryDto);
    }

    public boolean deleteCategory(ObjectId id) {
        Optional<Category> categoryToDelete = categoryRepository.findById(id);
        if (categoryToDelete.isEmpty()) {
            return false;
        }
        categoryRepository.delete(categoryToDelete.get());
        return true;
    }
}
