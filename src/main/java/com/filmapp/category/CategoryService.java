package com.filmapp.category;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.mapper = new ModelMapper();
    }

    public List<CategoryDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(c -> mapper.map(c, CategoryDto.class))
                .collect(Collectors.toList());
    }

    public CategoryDto save(CategoryDto categoryToCreate) {
        Category category = mapper.map(categoryToCreate, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return mapper.map(savedCategory, CategoryDto.class);
    }

    public CategoryDto update(CategoryDto categoryToUpdate) {
        if (categoryToUpdate == null)
            return null;
        return categoryRepository.findById(categoryToUpdate.getId())
                .map(c -> save(mapper.map(c, CategoryDto.class)))
                .orElse(null);

    }

    public boolean delete(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty())
            return false;

        categoryRepository.delete(category.get());
        return true;
    }
}
