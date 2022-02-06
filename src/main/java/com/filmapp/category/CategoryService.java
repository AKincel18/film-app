package com.filmapp.category;

import com.filmapp.category.exception.CannotAddCategoryException;
import com.filmapp.category.exception.CategoryNotExistException;
import com.filmapp.category.exception.DuplicatedCategoryException;
import com.filmapp.category.payload.CreateCategoryRequest;
import com.filmapp.category.payload.UpdateCategoryRequest;
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

    public CategoryDto save(CreateCategoryRequest request) throws CannotAddCategoryException, DuplicatedCategoryException {
        CategoryEnum categoryEnum = CategoryEnum.findCategoryEnum(request.getName());
        if (categoryRepository.existsByName(categoryEnum)) {
            throw new DuplicatedCategoryException();
        }
        request.setName(categoryEnum.name());
        Category category = mapper.map(request, Category.class);
        if (category == null || category.getName() == null) {
            throw new CannotAddCategoryException();
        }
        Category savedCategory = categoryRepository.save(category);
        return mapper.map(savedCategory, CategoryDto.class);
    }

    public CategoryDto update(UpdateCategoryRequest request) throws CannotAddCategoryException, CategoryNotExistException {
        if (request == null)
            throw new CannotAddCategoryException();
        Category category = findCategoryById(request.getId());
        try {
            category.setName(CategoryEnum.valueOf(request.getName()));
        } catch (IllegalArgumentException e) {
            throw new CannotAddCategoryException();
        }
        return mapper.map(categoryRepository.save(category), CategoryDto.class);
    }

    public boolean delete(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty())
            return false;

        categoryRepository.delete(category.get());
        return true;
    }

    public Category findCategoryById(Long id) throws CategoryNotExistException {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isEmpty())
            throw new CategoryNotExistException();
        return category.get();
    }
}
