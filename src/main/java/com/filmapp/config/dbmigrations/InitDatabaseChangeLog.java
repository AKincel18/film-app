package com.filmapp.config.dbmigrations;

import com.filmapp.category.Category;
import com.filmapp.category.CategoryEnum;
import com.filmapp.category.CategoryRepository;
import com.filmapp.role.person.PersonRole;
import com.filmapp.role.person.PersonRoleEnum;
import com.filmapp.role.person.PersonRoleRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

import java.util.Arrays;
import java.util.List;

@ChangeLog(order = "001")
@SuppressWarnings("unused")
public class InitDatabaseChangeLog {

    @ChangeSet(order = "001", id = "initCategories", author = "admin")
    public void initCategories(CategoryRepository categoryRepository) {
        List<Category> categories = Arrays.asList(
                new Category(CategoryEnum.COMEDY),
                new Category(CategoryEnum.THRILLER),
                new Category(CategoryEnum.FANTASY),
                new Category(CategoryEnum.ACTION),
                new Category(CategoryEnum.ROMANCE),
                new Category(CategoryEnum.WESTERN)
        );
        categoryRepository.saveAll(categories);
    }

    @ChangeSet(order = "002", id = "initRolesPerson", author = "admin")
    public void initRolesPerson(PersonRoleRepository personRoleRepository) {
        List<PersonRole> personRoles = Arrays.asList(
                new PersonRole(PersonRoleEnum.ACTOR),
                new PersonRole(PersonRoleEnum.DIRECTOR)
        );
        personRoleRepository.saveAll(personRoles);
    }
}
