package com.filmapp.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DictionaryRepository<T extends Dictionary> extends JpaRepository<T, Long> {
    boolean existsByName(String name);
}
