package com.gestionParcInformatique.gestionParcInformatique.Controllers;

import com.gestionParcInformatique.gestionParcInformatique.Models.RequestUtilisateurEntity;
import com.gestionParcInformatique.gestionParcInformatique.Models.Travailler;
import com.gestionParcInformatique.gestionParcInformatique.Models.Utilisateur;
import com.gestionParcInformatique.gestionParcInformatique.Service.TravailService;
import com.gestionParcInformatique.gestionParcInformatique.Service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;
    private final TravailService travailService;

    @PostMapping("")
    public ResponseEntity<?> createutilisateur(@RequestBody RequestUtilisateurEntity requestUtilisateurET) {
        try {
            System.out.println(requestUtilisateurET.getUtilisateur().getImmatricule());
            Utilisateur utilisateur = utilisateurService.createUtilisateur(requestUtilisateurET.getUtilisateur()) ;
            Travailler travaille = Travailler.builder()
                    .dateDebut(requestUtilisateurET.getDateDebut())
                    .utilisateur(utilisateur)
                    .entiteTravail(requestUtilisateurET.getEntiteTravail())
                    .build();
            var t = travailService.addTravail(travaille);
            return new ResponseEntity<>(utilisateur, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Utilisateur>> getAllUtilisateur() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs());
    }

    @GetMapping("/{idUtilisateur}")
    public Utilisateur getUtilisateur(@PathVariable long idUtilisateur) {
        return utilisateurService.getUtilisateur(idUtilisateur).get();
    }

    @PutMapping("/{idUtilisateur}")
    public Utilisateur updateUtilisateur(@RequestBody Utilisateur utilisateur, @PathVariable long idUtilisateur) {
        return utilisateurService.updtaeUtilisteur(utilisateur, idUtilisateur);
    }

    @DeleteMapping("/{idUtilisateur}")
    public String deleteUtilisateur(@PathVariable long idUtilisateur) throws Exception {
        return utilisateurService.deleteUtilisateur(idUtilisateur);
    }
}
