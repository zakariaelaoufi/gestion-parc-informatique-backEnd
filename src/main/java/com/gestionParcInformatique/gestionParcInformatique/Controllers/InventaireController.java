package com.gestionParcInformatique.gestionParcInformatique.Controllers;

import com.gestionParcInformatique.gestionParcInformatique.Models.Inventaire;
import com.gestionParcInformatique.gestionParcInformatique.Service.InventaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/inventaires")
public class InventaireController {
    @Autowired
    private InventaireService InventaireService;

    @GetMapping
    public ResponseEntity<List<Inventaire>> getAllInventaires() {
        try{
            List<Inventaire> Inventaires = InventaireService.getAllInventaire();
            return new ResponseEntity<>(Inventaires, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<Inventaire>> getAllAvailableInventaires() {
        return ResponseEntity.ok(InventaireService.getAllInventaireEnstock());
    }

    @GetMapping("/{idInventaire}")
    public ResponseEntity<Inventaire> getInventaireById(@PathVariable("idInventaire") long idInventaire) {
        Inventaire Inventaire = InventaireService.getInventaireById(idInventaire);
        return new ResponseEntity<>(Inventaire, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Inventaire> addInventaire(@RequestBody Inventaire Inventaire) {
        Inventaire newInventaire = InventaireService.addInventaire(Inventaire);
        return new ResponseEntity<>(newInventaire, HttpStatus.CREATED);
    }

    @PutMapping("/{idInventaire}")
    public ResponseEntity<Inventaire> updateInventaire(@PathVariable("idInventaire") long idInventaire, @RequestBody Inventaire Inventaire) {
        Inventaire updatedInventaire = InventaireService.updateInventaire(idInventaire, Inventaire);
        return new ResponseEntity<>(updatedInventaire, HttpStatus.OK);
    }

    @DeleteMapping("/{idInventaire}")
    public ResponseEntity<Void> deleteInventaire(@PathVariable("idInventaire") long idInventaire) {
        InventaireService.deleteInventaire(idInventaire);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
