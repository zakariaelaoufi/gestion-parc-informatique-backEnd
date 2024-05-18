package com.gestionParcInformatique.gestionParcInformatique.Controllers;

import com.gestionParcInformatique.gestionParcInformatique.Models.Travailler;
import com.gestionParcInformatique.gestionParcInformatique.Service.TravailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travail")
public class TravailController {
    @Autowired
    private TravailService travaillerService;

    @GetMapping
    public ResponseEntity<List<Travailler>> getAllTravaux() {
        List<Travailler> travaux = travaillerService.getAllTravaux();
        return new ResponseEntity<>(travaux, HttpStatus.OK);
    }

    @GetMapping("/{idTravail}")
    public ResponseEntity<Travailler> getTravailById(@PathVariable("idTravail") long idTravail) {
        Travailler travail = travaillerService.getTravailById(idTravail);
        return new ResponseEntity<>(travail, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Travailler> addTravail(@RequestBody Travailler travail) {
        Travailler newTravail = travaillerService.addTravail(travail);
        return new ResponseEntity<>(newTravail, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Travailler> updateTravail(@RequestBody Travailler travail) {
        Travailler updatedTravail = travaillerService.updateTravail(travail);
        return new ResponseEntity<>(updatedTravail, HttpStatus.OK);
    }

    @DeleteMapping("/{idTravail}")
    public ResponseEntity<Void> deleteTravail(@PathVariable("idTravail") long idTravail) {
        travaillerService.deleteTravail(idTravail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
