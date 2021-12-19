package com.filmapp.role.person;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRoleRepository extends MongoRepository<PersonRole, ObjectId> {

}
