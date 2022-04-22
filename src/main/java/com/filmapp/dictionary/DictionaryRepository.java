package com.filmapp.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface DictionaryRepository<T extends Dictionary> extends JpaRepository<T, Long> {
    Optional<T> findByName(String name);
    boolean existsByName(String name);
}
