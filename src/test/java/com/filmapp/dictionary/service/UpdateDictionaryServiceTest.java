package com.filmapp.dictionary.service;

import com.filmapp.category.Category;
import com.filmapp.category.CategoryRepository;
import com.filmapp.category.CategoryService;
import com.filmapp.dictionary.exceptions.DuplicatedDictionaryNameException;
import com.filmapp.dictionary.exceptions.NotExistedIdException;
import com.filmapp.dictionary.exceptions.NotProvidedIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpdateDictionaryServiceTest {

    private Category dictionary;

    @BeforeEach
    void setup() {
        dictionary = new Category();
        dictionary.setName("New category");
        dictionary.setId(-1L);
    }

    @Test
    @DisplayName("Update dictionary with duplicated name")
    void updateDictionary_duplicatedName_throwsDuplicatedDictionaryNameException() {
        //given
        var categoryRepositoryMock = mock(CategoryRepository.class);
        when(categoryRepositoryMock.existsByName(anyString())).thenReturn(true);
        //and
        when(categoryRepositoryMock.existsById(anyLong())).thenReturn(true);
        //and
        var categoryService = new CategoryService(categoryRepositoryMock);

        //when
        var exception = catchThrowable(() -> categoryService.update(dictionary));

        //then
        assertThat(exception).isInstanceOf(DuplicatedDictionaryNameException.class);
    }

    @Test
    @DisplayName("Update dictionary with id not existed")
    void updateDictionary_idNotExisted_throwsNotExistedIdException() {
        //given
        var categoryRepositoryMock = mock(CategoryRepository.class);
        when(categoryRepositoryMock.existsById(anyLong())).thenReturn(false);
        //and
        var categoryService = new CategoryService(categoryRepositoryMock);

        //when
        var exception = catchThrowable(() -> categoryService.update(dictionary));

        //then
        assertThat(exception).isInstanceOf(NotExistedIdException.class);
    }

    @Test
    @DisplayName("Update dictionary with id not provided")
    void updateDictionary_idNotProvided_throwsNotProvidedIdException() {
        //given
        var categoryRepositoryMock = mock(CategoryRepository.class);
        //and
        var categoryService = new CategoryService(categoryRepositoryMock);
        dictionary.setId(null);

        //when
        var exception = catchThrowable(() -> categoryService.update(dictionary));

        //then
        assertThat(exception).isInstanceOf(NotProvidedIdException.class);
    }

    @Test
    @DisplayName("Update dictionary with success")
    void updateDictionary_withSuccess() {
        //given
        var categoryRepositoryMock = mock(CategoryRepository.class);
        when(categoryRepositoryMock.existsById(anyLong())).thenReturn(true);
        when(categoryRepositoryMock.existsByName(anyString())).thenReturn(false);
        //and
        var categoryService = new CategoryService(categoryRepositoryMock);

        //when + then
        assertDoesNotThrow(() -> categoryService.update(dictionary));
    }


}