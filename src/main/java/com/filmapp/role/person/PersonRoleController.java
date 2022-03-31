package com.filmapp.role.person;

import com.filmapp.commons.exception.processing.MyExceptionProcessing;
import com.filmapp.dictionary.DictionaryController;
import com.filmapp.dictionary.DictionaryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MyExceptionProcessing
@RequestMapping("api/person-roles")
public class PersonRoleController extends DictionaryController<PersonRole> {

    public PersonRoleController(DictionaryService<PersonRole> dictionaryService) {
        super(dictionaryService);
    }
}
