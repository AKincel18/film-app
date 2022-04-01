package com.filmapp.dictionary;

import com.filmapp.commons.pagination.PaginationResponseEntity;
import com.filmapp.commons.pagination.PaginationResult;
import com.filmapp.dictionary.exceptions.DuplicatedDictionaryNameException;
import com.filmapp.dictionary.exceptions.NotExistedIdException;
import com.filmapp.dictionary.exceptions.NotProvidedIdException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

public class DictionaryController<T extends Dictionary> {

    private final DictionaryService<T> dictionaryService;

    public DictionaryController(DictionaryService<T> dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @GetMapping
    //@PreAuthorize("hasAnyRole({'ROLE_ADMIN', 'ROLE_MODERATOR'})")
    public ResponseEntity<List<T>> getAll(@RequestParam("pageSize") int pageSize,
                                          @RequestParam("pageIndex") int pageIndex) {
        PaginationResult<T> result = dictionaryService.getAll(pageSize, pageIndex);
        return PaginationResponseEntity.ok(result);
    }

    @PostMapping
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> create(@RequestBody @Valid T request) throws DuplicatedDictionaryNameException {
        T createdDictionary = dictionaryService.save(request);
        return ResponseEntity.created(URI.create("/" + createdDictionary.getId())).body(createdDictionary);
    }

    @PutMapping
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<?> update(@RequestBody @Valid T request)
            throws DuplicatedDictionaryNameException, NotProvidedIdException, NotExistedIdException {
        T updatedDictionary = dictionaryService.update(request);
        return ResponseEntity.created(URI.create("/" + updatedDictionary.getId())).body(updatedDictionary);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<List<?>> delete(@PathVariable Long id) {
        boolean isDeleted = dictionaryService.delete(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
