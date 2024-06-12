package com.gestionParcInformatique.gestionParcInformatique.Service;

import com.gestionParcInformatique.gestionParcInformatique.Models.Livraison;
import com.gestionParcInformatique.gestionParcInformatique.Repository.LivraisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivraisonService {
    @Autowired
    private LivraisonRepository livraisonRepository;

    public Livraison saveLivraison(Livraison livraison) {
        return livraisonRepository.save(livraison);
    }

    public List<Livraison> getAllLivraisons() {
        return livraisonRepository.findAll();
    }

    public Livraison getLivraisonByIdLivraison(Long idLivraison) {
        return livraisonRepository.findById(idLivraison).orElse(null);
    }

    public Livraison updateLivraison(Long idLivraison, Livraison livraison) {
        Livraison existingLivraison = livraisonRepository.findById(idLivraison).orElse(null);
        if (existingLivraison != null) {
            existingLivraison.setDateLivraison(livraison.getDateLivraison());
            existingLivraison.setDelai(livraison.getDelai());
            existingLivraison.setPrix(livraison.getPrix());
            existingLivraison.setFournisseur(livraison.getFournisseur());
            return livraisonRepository.save(existingLivraison);
        }
        return null;
    }

    public void deleteLivraison(Long idLivraison) {
        livraisonRepository.deleteById(idLivraison);
    }
}
