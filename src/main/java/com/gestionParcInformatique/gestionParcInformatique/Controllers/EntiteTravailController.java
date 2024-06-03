package com.gestionParcInformatique.gestionParcInformatique.Controllers;

import com.gestionParcInformatique.gestionParcInformatique.Models.EntiteTravail;
import com.gestionParcInformatique.gestionParcInformatique.Service.EntiteTravailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/entitetravail")
public class EntiteTravailController {
    @Autowired
    private EntiteTravailService entiteTravailService;

    @GetMapping
    public ResponseEntity<List<EntiteTravail>> getAllEntities() {
        List<EntiteTravail> entities = entiteTravailService.getAllEntities();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{idEntiteTravail}")
    public ResponseEntity<EntiteTravail> getEntityById(@PathVariable("idEntiteTravail") long idEntiteTravail) {
        EntiteTravail entity = entiteTravailService.getEntityById(idEntiteTravail);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EntiteTravail> addEntity(@RequestBody EntiteTravail entity) {
        if (entity.getParent().getIdEntiteTravail()==0)
            entity.setParent(null);
        EntiteTravail newEntity = entiteTravailService.addEntity(entity);
        return new ResponseEntity<>(newEntity, HttpStatus.CREATED);
    }

    @PutMapping("/{idEntiteTravail}")
    public ResponseEntity<EntiteTravail> updateEntity(@PathVariable("idEntiteTravail") long idEntiteTravail, @RequestBody EntiteTravail entity) {
        if (entity.getParent().getIdEntiteTravail()==0)
            entity.setParent(null);
        EntiteTravail updatedEntity = entiteTravailService.updateEntity(idEntiteTravail, entity);
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }

    @DeleteMapping("/{idEntiteTravail}")
    public ResponseEntity<Void> deleteEntity(@PathVariable("idEntiteTravail") long idEntiteTravail) {
        entiteTravailService.deleteEntity(idEntiteTravail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
