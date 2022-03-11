package com.filmapp.category;

import com.filmapp.commons.exception.processing.MyExceptionProcessing;
import com.filmapp.dictionary.DictionaryController;
import com.filmapp.dictionary.DictionaryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MyExceptionProcessing
@RequestMapping("api/categories")
public class CategoryController extends DictionaryController<Category> {

    public CategoryController(DictionaryService<Category> dictionaryService) {
        super(dictionaryService);
    }
}
