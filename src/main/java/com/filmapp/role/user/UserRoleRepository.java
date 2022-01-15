package com.filmapp.role.user;

import com.filmapp.generic.GenericRepository;

import java.util.Optional;

public interface UserRoleRepository extends GenericRepository<UserRole> {
    Optional<UserRole> findByName(String name);
}
