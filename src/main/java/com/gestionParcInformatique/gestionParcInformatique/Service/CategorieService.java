package com.gestionParcInformatique.gestionParcInformatique.Service;

import com.gestionParcInformatique.gestionParcInformatique.Models.Categorie;
import com.gestionParcInformatique.gestionParcInformatique.Repository.CategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategorieService {
    private final CategorieRepository categorieRepository;

    public long totalCategories() {
        return categorieRepository.count();
    }

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorieById(long idCategorie) {
        return categorieRepository.findById(idCategorie).orElse(null);
    }

    public Categorie addCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public Categorie updateCategorie(long idCategorie, Categorie updatedCategorie) {
        if (categorieRepository.existsById(idCategorie)) {
            updatedCategorie.setIdCategorie(idCategorie);
            return categorieRepository.save(updatedCategorie);
        }
        return null;
    }

    public void deleteCategorie(long idCategorie) {
        categorieRepository.deleteById(idCategorie);
    }
}
