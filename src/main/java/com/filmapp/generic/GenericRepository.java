package com.filmapp.generic;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenericRepository<Entity extends BaseEntity> extends MongoRepository<Entity, ObjectId> {
    Entity getByName(String name);
}
