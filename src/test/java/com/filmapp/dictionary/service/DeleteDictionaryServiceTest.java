package com.filmapp.dictionary.service;

import com.filmapp.category.Category;
import com.filmapp.category.CategoryRepository;
import com.filmapp.category.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteDictionaryServiceTest {

    @Test
    @DisplayName("Delete dictionary with an id that does not exist")
    void deleteDictionary_unknownId_shouldReturnFalse() {
        //given + when
        boolean result = deleteDictionary(Optional.empty());

        //then
        assertFalse(result);
    }

    @Test
    @DisplayName("Delete dictionary with an id that does exist")
    void deleteDictionary_knownId_shouldReturnTrue() {
        //given + when
        Category dictionary = Category.builder().id(0L).name("Category").build();
        boolean result = deleteDictionary(Optional.of(dictionary));

        //then
        assertTrue(result);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private boolean deleteDictionary(Optional<Category> dictionary) {
        //given
        var categoryRepositoryMock = mock(CategoryRepository.class);
        when(categoryRepositoryMock.findById(anyLong())).thenReturn(dictionary);
        //and
        var categoryService = new CategoryService(categoryRepositoryMock);

        //then
        return categoryService.delete(0L);
    }
}
