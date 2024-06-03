package com.gestionParcInformatique.gestionParcInformatique.Controllers;

import com.gestionParcInformatique.gestionParcInformatique.Models.*;
import com.gestionParcInformatique.gestionParcInformatique.Service.*;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/home")
@Data
public class HomeController {
    private final UtilisateurService utilisateurService;
    private final EntiteTravailService entiteTravailService;
    private final InventaireService inventaireService;
    private final ProduitService produitService;
    private  final CategorieService categorieService;

    @GetMapping("/uppercardinfo")
    public ResponseEntity<?> uppercardinfo() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalUtilisateur", utilisateurService.getTotalUtilisateurs());
        data.put("totalEntiteTravail", entiteTravailService.totalEntiteTravail());
        data.put("totalInventaire", inventaireService.totalInventaire());
        data.put("totalProduit", produitService.totalProduit());
        return ResponseEntity.ok(data);
    }

    @GetMapping("/machinecategorieinfo")
    public ResponseEntity<Map<String, Object>> machineCategorieInfo() {
        List<Categorie> categorieList = categorieService.getAllCategories();
        Map<String, Object> data = new HashMap<>();
        for (Categorie categorie : categorieList) {
            int totalInventorySize = produitService.findAllByCategorie(categorie)
                    .stream()
                    .mapToInt(e -> e.getInventaireList().size())
                    .sum();
            data.put(categorie.getLibelle(), totalInventorySize);
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping("/pieetatinfo")
    public Map<String, Integer> getNbEtatInv() {
        List<Inventaire> inventaireList = inventaireService.getAllInventaire();
        Map<String, Integer> data = new HashMap<>();
        int nbEnsStock = 0, nbActif = 0, nbEnReparation = 0,nbReforme = 0;
        for (Inventaire inventaire : inventaireList) {
            if (Etat.ENSTOCK == inventaire.getEtat())
                nbEnsStock++;
            else if (Etat.ACTIF == inventaire.getEtat())
                nbActif++;
            else if (Etat.ENREPARATION == inventaire.getEtat())
                nbEnReparation++;
            else if (Etat.REFORME == inventaire.getEtat()) {
                nbReforme++;
            }
        }
        data.put("ENSTOCK", nbEnsStock);
        data.put("ACTIF", nbActif);
        data.put("ENREPARATION", nbEnReparation);
        data.put("REFORME", nbReforme);

        return data;
    }

    @GetMapping("/machine-count-department")
    public Map<String, Integer> getMachineCountByDepartment() {
        return entiteTravailService.getMachineCountByDepartment();
    }

    @GetMapping("/top5actif")
    public List<ProduitResp> getTop5ActiveProduits() {
        List<Produit> produitList = produitService.getAllProduits();

        return produitList.stream()
                .map(p -> (Produit) p)  // Ensure the list elements are cast to Produit
                .sorted(Comparator.comparingInt(p -> ((Produit) p).getNbEtatInv().get("ACTIF")).reversed())
                .limit(3)
                .map(p -> new ProduitResp(p.getIdProduit(),p.getNomProduit(), p.getImageURL(), p.getTotalPiece(),p.getNbEtatInv().get("ACTIF")))
                .collect(Collectors.toList());
    }
}
