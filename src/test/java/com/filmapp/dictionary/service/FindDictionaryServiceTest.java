package com.filmapp.dictionary.service;

import com.filmapp.category.Category;
import com.filmapp.category.CategoryRepository;
import com.filmapp.category.CategoryService;
import com.filmapp.commons.exception.NotExistException;
import com.filmapp.dictionary.DictionaryType;
import com.filmapp.dictionary.exceptions.DictionaryNotExistException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindDictionaryServiceTest {

    @Test
    @DisplayName("Find dictionary by id with optional empty")
    void findDictionaryById_returnEmpty_throwsDictionaryNotExistException() {
        //given
        var categoryRepositoryMock = mock(CategoryRepository.class);
        when(categoryRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());
        //and
        var categoryService = new CategoryService(categoryRepositoryMock);

        //when
        var exception = Assertions.catchThrowable(() -> categoryService.findById(-1L, DictionaryType.CATEGORY));

        //then
        assertThat(exception).isInstanceOf(DictionaryNotExistException.class);
    }

    @Test
    @DisplayName("Find dictionary by id with success")
    void findDictionaryById_success() throws NotExistException {
        //given
        Category dictionary = Category.builder().id(-1L).name("Category").build();
        var categoryRepositoryMock = mock(CategoryRepository.class);
        when(categoryRepositoryMock.findById(anyLong())).thenReturn(Optional.of(dictionary));
        //and
        var categoryService = new CategoryService(categoryRepositoryMock);

        //when
        var result = categoryService.findById(-1L, DictionaryType.CATEGORY);

        //then
        assertEquals(result, dictionary);
    }
}
