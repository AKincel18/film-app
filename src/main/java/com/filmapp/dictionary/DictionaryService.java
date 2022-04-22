package com.filmapp.dictionary;

import com.filmapp.commons.exception.NotExistException;
import com.filmapp.dictionary.exceptions.DictionaryNotExistException;
import com.filmapp.dictionary.exceptions.DuplicatedDictionaryNameException;
import com.filmapp.dictionary.exceptions.NotExistedIdException;
import com.filmapp.dictionary.exceptions.NotProvidedIdException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public abstract class DictionaryService<T extends Dictionary> {

    private final DictionaryRepository<T> dictionaryRepository;

    public DictionaryService(DictionaryRepository<T> dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    public T save(T dictionary) throws DuplicatedDictionaryNameException {
        checkSaveCondition(dictionary.getName());
        return dictionaryRepository.save(dictionary);
    }

    private void checkSaveCondition(String name) throws DuplicatedDictionaryNameException {
        checkDuplicatedName(name);
    }

    public T update(T dictionary) throws DuplicatedDictionaryNameException, NotProvidedIdException, NotExistedIdException {
        checkUpdateConditions(dictionary);
        return save(dictionary);
    }

    public T findById(Long id, DictionaryType dictionaryType) throws NotExistException {
        Optional<T> dictionary = dictionaryRepository.findById(id);
        return dictionary.orElseThrow(() -> new DictionaryNotExistException(dictionaryType.getName()));
    }

    private void checkUpdateConditions(T dictionary)
            throws DuplicatedDictionaryNameException, NotProvidedIdException, NotExistedIdException {
        checkProvidedId(dictionary.getId());
        checkExistingId(dictionary.getId());
        checkDuplicatedName(dictionary.getName());
    }

    private void checkExistingId(Long id) throws NotExistedIdException {
        boolean isExist = dictionaryRepository.existsById(id);
        if (!isExist) {
            throw new NotExistedIdException();
        }
    }

    private void checkProvidedId(Long id) throws NotProvidedIdException {
        if (id == null) {
            throw new NotProvidedIdException();
        }
    }

    private void checkDuplicatedName(String dictionaryName) throws DuplicatedDictionaryNameException {
        boolean isDuplicated = dictionaryRepository.existsByName(dictionaryName);
        if (isDuplicated) {
            throw new DuplicatedDictionaryNameException(dictionaryName);
        }
    }

    public boolean delete(Long id) {
        Optional<T> dictionary = dictionaryRepository.findById(id);
        if (dictionary.isPresent()) {
            dictionaryRepository.delete(dictionary.get());
            return true;
        }
        return false;
    }

    public Page<T> findAll(Pageable pageable) {
        return dictionaryRepository.findAll(pageable);
    }
}
