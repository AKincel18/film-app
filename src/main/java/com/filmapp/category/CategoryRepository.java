package com.filmapp.category;

import com.filmapp.dictionary.DictionaryRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends DictionaryRepository<Category> {
}
