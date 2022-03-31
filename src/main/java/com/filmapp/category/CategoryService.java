package com.filmapp.category;

import com.filmapp.dictionary.DictionaryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends DictionaryService<Category> {

    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository);
    }
}
