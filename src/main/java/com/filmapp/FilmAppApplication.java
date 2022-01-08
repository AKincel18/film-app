package com.filmapp;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableMongock
public class FilmAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
