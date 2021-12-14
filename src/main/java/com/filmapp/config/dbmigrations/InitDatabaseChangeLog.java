package com.filmapp.config.dbmigrations;

import com.filmapp.category.Category;
import com.filmapp.category.CategoryRepository;
import com.filmapp.role.person.RolePerson;
import com.filmapp.role.person.RolePersonRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

import java.util.Arrays;
import java.util.List;

@ChangeLog(order = "001")
public class InitDatabaseChangeLog {

    @ChangeSet(order = "001", id = "initCategories", author = "admin")
    public void initCategories(CategoryRepository categoryRepository) {
        List<Category> categories = Arrays.asList(
                new Category("Comedy"),
                new Category("Thriller"),
                new Category("Fantasy"),
                new Category("Action"),
                new Category("Romance"),
                new Category("Western")
        );
        categoryRepository.saveAll(categories);
    }

    @ChangeSet(order = "002", id = "initRolesPerson", author = "admin")
    public void initRolesPerson(RolePersonRepository rolePersonRepository) {
        List<RolePerson> rolePeople = Arrays.asList(
                new RolePerson("Actor"),
                new RolePerson("Director")
        );
        rolePersonRepository.saveAll(rolePeople);
    }
}
