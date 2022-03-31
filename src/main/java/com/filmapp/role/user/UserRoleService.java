package com.filmapp.role.user;

import com.filmapp.dictionary.DictionaryRepository;
import com.filmapp.dictionary.DictionaryService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService extends DictionaryService<UserRole> {

    public UserRoleService(DictionaryRepository<UserRole> dictionaryRepository) {
        super(dictionaryRepository);
    }
}
