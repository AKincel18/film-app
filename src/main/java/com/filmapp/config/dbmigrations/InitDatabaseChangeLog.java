package com.filmapp.config.dbmigrations;

import com.filmapp.category.Category;
import com.filmapp.category.CategoryEnum;
import com.filmapp.category.CategoryRepository;
import com.filmapp.role.person.PersonRole;
import com.filmapp.role.person.PersonRoleEnum;
import com.filmapp.role.person.PersonRoleRepository;
import com.filmapp.role.user.UserRole;
import com.filmapp.role.user.UserRoleEnum;
import com.filmapp.role.user.UserRoleRepository;
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
                new Category(CategoryEnum.COMEDY.getName()),
                new Category(CategoryEnum.THRILLER.getName()),
                new Category(CategoryEnum.FANTASY.getName()),
                new Category(CategoryEnum.ACTION.getName()),
                new Category(CategoryEnum.ROMANCE.getName()),
                new Category(CategoryEnum.WESTERN.getName())
        );
        categoryRepository.saveAll(categories);
    }

    @ChangeSet(order = "002", id = "initRolesPerson", author = "admin")
    public void initPersonRoles(PersonRoleRepository personRoleRepository) {
        List<PersonRole> personRoles = Arrays.asList(
                new PersonRole(PersonRoleEnum.ACTOR.getName()),
                new PersonRole(PersonRoleEnum.DIRECTOR.getName())
        );
        personRoleRepository.saveAll(personRoles);
    }

    @ChangeSet(order = "003", id = "initRolesUser", author = "admin")
    public void initUserRoles(UserRoleRepository userRoleRepository) {
        List<UserRole> userRoles = Arrays.asList(
                new UserRole(UserRoleEnum.ROLE_ADMIN.name()),
                new UserRole(UserRoleEnum.ROLE_USER.name())
        );
        userRoleRepository.saveAll(userRoles);
    }
}
