package com.filmapp.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*@Service
public class GenericServiceImpl<Entity extends BaseEntity> implements GenericService<Entity> {

    @Autowired
    protected GenericRepository<Entity> genericRepository;

    public List<Entity> getAll() {
        return genericRepository.findAll();
    }

    public Entity save(Entity Entity) {
        return genericRepository.save(Entity);
    }

    public Entity update(Entity entity) {
        if (entity == null)
            return null;
        Optional<Entity> entityToUpdate = genericRepository.findById(1l);
        if (entityToUpdate.isEmpty())
            return null;
        return save(entity);
    }

    public boolean delete(Long id) {
        Optional<Entity> entityToDelete = genericRepository.findById(id);
        if (entityToDelete.isEmpty()) {
            return false;
        }
        genericRepository.delete(entityToDelete.get());
        return true;
    }
}*/
