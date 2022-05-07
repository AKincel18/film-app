package com.filmapp.config;

import com.filmapp.film.FilmMapper;
import com.filmapp.person.PersonMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PersonMapper personMapper() {
        return new PersonMapper();
    }

    @Bean
    public FilmMapper filmMapper() {
        return new FilmMapper();
    }
}
