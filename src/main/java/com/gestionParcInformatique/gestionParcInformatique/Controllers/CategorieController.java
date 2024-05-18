package com.gestionParcInformatique.gestionParcInformatique.Controllers;

import com.gestionParcInformatique.gestionParcInformatique.Models.Categorie;
import com.gestionParcInformatique.gestionParcInformatique.Service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {
    private final CategorieService categorieService;

    @Autowired
    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieService.getAllCategories();
    }

    @GetMapping("/{idCategorie}")
    public Categorie getCategorieById(@PathVariable("idCategorie") long idCategorie) {
        return categorieService.getCategorieById(idCategorie);
    }

    @PostMapping
    public Categorie addCategorie(@RequestBody Categorie categorie) {
        return categorieService.addCategorie(categorie);
    }

    @PutMapping("/{idCategorie}")
    public Categorie updateCategorie(@PathVariable("idCategorie") long idCategorie, @RequestBody Categorie updatedCategorie) {
        return categorieService.updateCategorie(idCategorie, updatedCategorie);
    }

    @DeleteMapping("/{idCategorie}")
    public void deleteCategorie(@PathVariable("idCategorie") long idCategorie) {
        categorieService.deleteCategorie(idCategorie);
    }
}
