package com.filmapp.config.dbmigrations;

import com.filmapp.role.user.UserRole;
import com.filmapp.role.user.UserRoleEnum;
import com.filmapp.role.user.UserRoleRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

@ChangeLog(order = "002")
@SuppressWarnings("unused")
public class AddedModeratorRoleChangeLog {

    @ChangeSet(order = "004", id = "addedModeratorRole", author = "admin")
    public void addedModeratorRole(UserRoleRepository repository) {
        repository.save(new UserRole(UserRoleEnum.ROLE_MODERATOR.name()));
    }
}
