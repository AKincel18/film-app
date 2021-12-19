package com.filmapp.role.user;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRoleRepository extends MongoRepository<UserRole, ObjectId> {
    Optional<UserRole> findByName(UserRoleEnum name);
}
