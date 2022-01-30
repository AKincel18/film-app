package com.filmapp.category;

import com.filmapp.exception.CannotAddCategoryException;
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

    public CategoryDto save(CategoryDto categoryToCreate) throws CannotAddCategoryException {
        Category category = mapper.map(categoryToCreate, Category.class);
        if (category == null || category.getName() == null) {
            throw new CannotAddCategoryException();
        }
        Category savedCategory = categoryRepository.save(category);
        return mapper.map(savedCategory, CategoryDto.class);
    }

    public CategoryDto update(CategoryDto categoryToUpdate) throws CannotAddCategoryException {
        if (categoryToUpdate == null)
            throw new CannotAddCategoryException();
        Optional<Category> category = categoryRepository.findById(categoryToUpdate.getId());
        if (category.isEmpty())
            throw new CannotAddCategoryException();
        return save(mapper.map(category.get(), CategoryDto.class));
    }

    public boolean delete(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty())
            return false;

        categoryRepository.delete(category.get());
        return true;
    }
}
