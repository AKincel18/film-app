package com.filmapp.generic;

import org.bson.types.ObjectId;

import java.util.List;

public interface GenericService<Entity extends BaseEntity> {
    List<Entity> getAll();
    Entity save(Entity entity);
    Entity update(Entity entity);
    boolean delete(ObjectId id);
}
