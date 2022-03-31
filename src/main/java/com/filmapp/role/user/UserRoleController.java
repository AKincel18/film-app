package com.filmapp.role.user;

import com.filmapp.commons.exception.processing.MyExceptionProcessing;
import com.filmapp.dictionary.DictionaryController;
import com.filmapp.dictionary.DictionaryService;
import com.filmapp.dictionary.exceptions.DuplicatedDictionaryNameException;
import com.filmapp.dictionary.exceptions.NotExistedIdException;
import com.filmapp.dictionary.exceptions.NotProvidedIdException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@MyExceptionProcessing
@RequestMapping("api/user-roles")
public class UserRoleController extends DictionaryController<UserRole> {

    public UserRoleController(DictionaryService<UserRole> dictionaryService) {
        super(dictionaryService);
    }

    @Override
    public ResponseEntity<?> create(@RequestBody @Valid UserRole request) throws DuplicatedDictionaryNameException {
        request.setName(request.getName() != null ? request.getName().toUpperCase() : null);
        return super.create(request);
    }

    @Override
    public ResponseEntity<?> update(@RequestBody @Valid UserRole request)
            throws DuplicatedDictionaryNameException, NotProvidedIdException, NotExistedIdException {
        request.setName(request.getName() != null ? request.getName().toUpperCase() : null);
        return super.update(request);
    }
}
