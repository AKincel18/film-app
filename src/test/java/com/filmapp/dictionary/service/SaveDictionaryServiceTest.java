package com.filmapp.dictionary.service;

import com.filmapp.category.Category;
import com.filmapp.category.CategoryRepository;
import com.filmapp.category.CategoryService;
import com.filmapp.dictionary.exceptions.DuplicatedDictionaryNameException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SaveDictionaryServiceTest {

    private Category dictionary;

    @BeforeEach
    void setup() {
        dictionary = new Category();
        dictionary.setName("New category");
        dictionary.setId(-1L);
    }

    @Test
    @DisplayName("Save dictionary with duplicated name")
    void saveDictionary_duplicatedName_throwsDuplicatedDictionaryNameException() {
        //given
        var categoryRepositoryMock = mock(CategoryRepository.class);
        when(categoryRepositoryMock.existsByName(anyString())).thenReturn(true);
        //and
        var categoryService = new CategoryService(categoryRepositoryMock);

        //when
        var exception = Assertions.catchThrowable(() -> categoryService.save(dictionary));

        //then
        assertThat(exception).isInstanceOf(DuplicatedDictionaryNameException.class);
    }

    @Test
    @DisplayName("Save dictionary with success")
    void saveDictionary_success() throws DuplicatedDictionaryNameException {
        //given
        var categoryRepositoryMock = mock(CategoryRepository.class);
        when(categoryRepositoryMock.save(dictionary)).thenReturn(dictionary);
        //and
        var categoryService = new CategoryService(categoryRepositoryMock);

        //when
        var result = categoryService.save(dictionary);

        //then
        assertEquals(result, dictionary);

    }
}
