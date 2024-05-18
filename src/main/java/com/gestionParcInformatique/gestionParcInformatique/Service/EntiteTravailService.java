package com.gestionParcInformatique.gestionParcInformatique.Service;

import com.gestionParcInformatique.gestionParcInformatique.Models.EntiteTravail;
import com.gestionParcInformatique.gestionParcInformatique.Repository.EntiteTravailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntiteTravailService {
    @Autowired
    private EntiteTravailRepository entiteTravailRepository;

    public List<EntiteTravail> getAllEntities() {
        return entiteTravailRepository.findAll();
    }

    public EntiteTravail getEntityById(long id) {
        Optional<EntiteTravail> optionalEntity = entiteTravailRepository.findById(id);
        return optionalEntity.orElse(null);
    }

    public EntiteTravail addEntity(EntiteTravail entity) {
        return entiteTravailRepository.save(entity);
    }

    public EntiteTravail updateEntity(long id, EntiteTravail entity) {
        if (entiteTravailRepository.existsById(id)) {
            entity.setIdEntiteTravail(id);
            return entiteTravailRepository.save(entity);
        }
        return null;
    }

    public void deleteEntity(long id) {
        entiteTravailRepository.deleteById(id);
    }
}
