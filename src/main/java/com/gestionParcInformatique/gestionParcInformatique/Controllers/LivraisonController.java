package com.gestionParcInformatique.gestionParcInformatique.Controllers;

import com.gestionParcInformatique.gestionParcInformatique.Models.Livraison;
import com.gestionParcInformatique.gestionParcInformatique.Service.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livraisons")
public class LivraisonController {

    @Autowired
    private LivraisonService livraisonService;

    @PostMapping
    public ResponseEntity<Livraison> createLivraison(@RequestBody Livraison livraison) {
        Livraison savedLivraison = livraisonService.saveLivraison(livraison);
        return ResponseEntity.ok(savedLivraison);
    }

    @GetMapping
    public ResponseEntity<List<Livraison>> getAllLivraisons() {
        List<Livraison> livraisons = livraisonService.getAllLivraisons();
        return ResponseEntity.ok(livraisons);
    }

    @GetMapping("/{idLivraison}")
    public ResponseEntity<Livraison> getLivraisonByIdLivraison(@PathVariable Long idLivraison) {
        Livraison livraison = livraisonService.getLivraisonByIdLivraison(idLivraison);
        if (livraison != null) {
            return ResponseEntity.ok(livraison);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{idLivraison}")
    public ResponseEntity<Livraison> updateLivraison(@PathVariable Long idLivraison, @RequestBody Livraison livraison) {
        Livraison updatedLivraison = livraisonService.updateLivraison(idLivraison, livraison);
        if (updatedLivraison != null) {
            return ResponseEntity.ok(updatedLivraison);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idLivraison}")
    public ResponseEntity<Void> deleteLivraison(@PathVariable Long idLivraison) {
        livraisonService.deleteLivraison(idLivraison);
        return ResponseEntity.noContent().build();
    }
}