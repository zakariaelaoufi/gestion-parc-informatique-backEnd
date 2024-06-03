package com.gestionParcInformatique.gestionParcInformatique.Controllers;

import com.gestionParcInformatique.gestionParcInformatique.Models.Etat;
import com.gestionParcInformatique.gestionParcInformatique.Models.Inventaire;
import com.gestionParcInformatique.gestionParcInformatique.Models.Produit;
import com.gestionParcInformatique.gestionParcInformatique.Service.InventaireService;
import com.gestionParcInformatique.gestionParcInformatique.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/inventaires")
public class InventaireController {
    @Autowired
    private InventaireService InventaireService;
    @Autowired
    private ProduitService produitService;
    @Autowired

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

    @PostMapping("/addmore")
    public ResponseEntity<String> addMoreInventaire(@RequestBody Map<String, Object> inventaireRequest) {
         long idProduit = (Integer) inventaireRequest.get("idProduit");
        int quantite = (Integer) inventaireRequest.get("quantite");
        List<String> numerosSerie = (List<String>) inventaireRequest.get("numeroSerie");

        Produit produit = produitService.getProduitById(idProduit);
        List<Inventaire> inventaires = new ArrayList<>(quantite);
        for (int i=0;i<quantite;i++) {
            inventaires.add(Inventaire.builder()
                    .produit(produit)
                    .etat(Etat.ENSTOCK)
                    .numeroSerie(numerosSerie.get(i))
                    .build());
        }
        List<Inventaire> savedInventaires = InventaireService.addAllInventaires(inventaires);
        return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
    }

    @PutMapping("/{idInventaire}")
    public ResponseEntity<Inventaire> updateInventaire(@PathVariable("idInventaire") long idInventaire, @RequestBody Map<String, Object> inventaireRequest) {
        long idProduit = (Integer) inventaireRequest.get("idProduit");
        String numeroSerie = (String) inventaireRequest.get("numeroSerie");
        String etatString = (String) inventaireRequest.get("etat");
        Etat etat = Etat.valueOf(etatString);

        Produit produit = produitService.getProduitById(idProduit);
        Inventaire inventaire = Inventaire.builder()
                .produit(produit)
                .etat(etat)
                .numeroSerie(numeroSerie)
                .build();

        Inventaire updatedInventaire = InventaireService.updateInventaire(idInventaire, inventaire);
        return new ResponseEntity<>(updatedInventaire, HttpStatus.OK);
    }

    @DeleteMapping("/{idInventaire}")
    public ResponseEntity<Void> deleteInventaire(@PathVariable("idInventaire") long idInventaire) {
        InventaireService.deleteInventaire(idInventaire);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
