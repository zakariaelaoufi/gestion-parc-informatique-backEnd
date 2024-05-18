package com.gestionParcInformatique.gestionParcInformatique.Controllers;

import com.gestionParcInformatique.gestionParcInformatique.Models.Fournisseur;
import com.gestionParcInformatique.gestionParcInformatique.Service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fournisseur")
public class FournisseurController {
    private FournisseurService fournisseurService;
    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @PostMapping("")
    @ResponseStatus
    public ResponseEntity<Fournisseur> createFournisseur(@RequestBody Fournisseur fournisseur) {
        return new ResponseEntity<Fournisseur>(fournisseurService.createFournisseur(fournisseur), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Fournisseur>> getAllFournisseur() {
        return ResponseEntity.ok(fournisseurService.getAllFournisseur());
    }

    @GetMapping("/{idFournisseur}")
    public ResponseEntity<Optional<Fournisseur>> getFournisseur(@PathVariable long idFournisseur){
        return ResponseEntity.ok(fournisseurService.getFournisseur(idFournisseur));
    }

    @PutMapping("/{idFournisseur}")
    public Fournisseur updateFournisseur(@RequestBody Fournisseur fournisseur, @PathVariable long idFournisseur) {
        return fournisseurService.updateFournisseur(fournisseur,idFournisseur);
    }

    @DeleteMapping("/{idFournisseur}")
    public String deleteFournisseur(@PathVariable long idFournisseur) {
        return fournisseurService.deleteFournisseur(idFournisseur);
    }
}
