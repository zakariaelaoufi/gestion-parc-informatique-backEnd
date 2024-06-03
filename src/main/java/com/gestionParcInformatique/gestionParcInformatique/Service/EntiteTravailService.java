package com.gestionParcInformatique.gestionParcInformatique.Service;

import com.gestionParcInformatique.gestionParcInformatique.Models.*;
import com.gestionParcInformatique.gestionParcInformatique.Repository.EntiteTravailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EntiteTravailService {
    @Autowired
    private EntiteTravailRepository entiteTravailRepository;

    public int totalEntiteTravail() {
        return entiteTravailRepository.countAllByDeletedIsFalse();
    }

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

    public Map<String, Integer> getMachineCountByDepartment() {
        List<EntiteTravail> allEntities = entiteTravailRepository.findAll();
        Map<String, Integer> departmentMachineCount = new HashMap<>();

        for (EntiteTravail entity : allEntities) {
            if (entity.getTypeEntiteTravail() == Type.DIVISION || entity.getTypeEntiteTravail() == Type.SERVICE || entity.getTypeEntiteTravail() == Type.DEPARTEMENT) {
                int machineCount = countMachines(entity);
                propagateCountToDepartment(entity, machineCount, departmentMachineCount);
            }
        }

        return departmentMachineCount;
    }

    private int countMachines(EntiteTravail entity) {
        long affecterCount = entity.getTravaillerList().stream()
                .flatMap(travail -> travail.getAffecters().stream())
                .filter(affecter -> affecter.getDateRetoure() == null)
                .count();

        long attacherCount = entity.getAttacherList().stream()
                .filter(attacher -> attacher.getDateRetoure() == null)
                .count();

        return (int) (affecterCount + attacherCount);
    }

    private void propagateCountToDepartment(EntiteTravail entity, int count, Map<String, Integer> departmentMachineCount) {
        EntiteTravail current = entity;
        while (current != null && current.getTypeEntiteTravail() != Type.DEPARTEMENT) {
            current = current.getParent();
        }

        if (current != null && !current.isDeleted()) {
            departmentMachineCount.put(
                    current.getNomEntiteTravail(),
                    departmentMachineCount.getOrDefault(current.getNomEntiteTravail(), 0) + count
            );
        }
    }
}
