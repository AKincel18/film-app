package com.filmapp.person;

import com.filmapp.role.person.PersonRole;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PersonRepository extends MongoRepository<Person, ObjectId> {
    List<Person> findPeopleByRole(PersonRole role);
}
