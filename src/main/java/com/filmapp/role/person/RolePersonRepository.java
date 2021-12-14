package com.filmapp.role.person;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePersonRepository extends MongoRepository<RolePerson, ObjectId> {

}
