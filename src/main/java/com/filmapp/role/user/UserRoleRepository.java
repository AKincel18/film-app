package com.filmapp.role.user;

import com.filmapp.dictionary.DictionaryRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends DictionaryRepository<UserRole> {
}
