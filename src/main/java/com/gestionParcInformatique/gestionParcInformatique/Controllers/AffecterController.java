package com.gestionParcInformatique.gestionParcInformatique.Controllers;

import com.gestionParcInformatique.gestionParcInformatique.Models.*;
import com.gestionParcInformatique.gestionParcInformatique.Service.AffecterService;
import com.gestionParcInformatique.gestionParcInformatique.Service.InventaireService;
import com.gestionParcInformatique.gestionParcInformatique.Service.TravailService;
import com.gestionParcInformatique.gestionParcInformatique.Service.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/affectation")
@CrossOrigin
public class AffecterController {
    private static final Logger log = LoggerFactory.getLogger(AffecterController.class);
    @Autowired
    private AffecterService affecterService;
    @Autowired
    private TravailService travailService;
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private InventaireService inventaireService;

    @GetMapping
    public ResponseEntity<List<Affecter>> getAllAffectations() {
        List<Affecter> affectations = affecterService.getAllAffectations();
        return new ResponseEntity<>(affectations, HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Affecter>> getAvailableAffectations() {
        List<Affecter> affectations = affecterService.getAllAvailableAffectations();
        return new ResponseEntity<>(affectations, HttpStatus.OK);
    }


    @GetMapping("/{idAffectation}")
    public ResponseEntity<Affecter> getAffectationById(@PathVariable("idAffectation") long idAffectation) {
        try {
            Affecter affectation = affecterService.getAffectationById(idAffectation);
            return new ResponseEntity<>(affectation, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Affecter> addAffectation(@RequestBody Map<String, Object> affectationReq) {
        long idInventaire = (Integer) affectationReq.get("idInventaire");
        long idTravail = (Integer) affectationReq.get("idTravail");
        LocalDate dateAffectation =  LocalDate.parse((String) affectationReq.get("dateAffectation"));

        Travailler travailler = travailService.getTravailById(idTravail);
        Inventaire inventaire = inventaireService.getInventaireById(idInventaire);

        Affecter affectation = Affecter.builder()
                .inventaire(inventaire)
                .travailler(travailler)
                .dateAffectation(dateAffectation)
                .build();
        Affecter newAffectation = affecterService.addAffectation(affectation);

        inventaire.setEtat(Etat.ACTIF);
        inventaireService.updateInventaire(inventaire.getIdInventaire(),inventaire);

        return new ResponseEntity<>(newAffectation, HttpStatus.CREATED);
    }

    @PutMapping("/{idAffectation}")
    public ResponseEntity<Affecter> updateAffectation(@PathVariable("idAffectation") long idAffectation, @RequestBody Map<Object, Object> affectation) {
        LocalDate dateAffectation =  LocalDate.parse((String) affectation.get("dateAffectation"));
        LocalDate dateRetour = LocalDate.parse((String) affectation.get("dateRetour"));

        long idInventaire = (Integer) affectation.get("idInventaire");
        long idTravail =  (Integer) affectation.get("idTravail");

        Travailler travailler = travailService.getTravailById(idTravail);
        Inventaire inventaire = inventaireService.getInventaireById(idInventaire);

        Affecter updatedAffectation = Affecter.builder()
                .idAffectation(idAffectation)
                .dateAffectation(dateAffectation)
                .dateRetoure(dateRetour)
                .travailler(travailler)
                .inventaire(inventaire)
                .build();
        Affecter updateReq = affecterService.updateAffectation(idAffectation, updatedAffectation);

        inventaire.setEtat(Etat.ENSTOCK);
        inventaireService.updateInventaire(inventaire.getIdInventaire(),inventaire);

        return new ResponseEntity<>(updatedAffectation, HttpStatus.OK);
    }

    @DeleteMapping("/{idAffectation}")
    public ResponseEntity<Void> deleteAffectation(@PathVariable("idAffectation") long idAffectation) {
        affecterService.deleteAffectation(idAffectation);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
