package com.gestionParcInformatique.gestionParcInformatique.Service;

import com.gestionParcInformatique.gestionParcInformatique.Models.Produit;
import com.gestionParcInformatique.gestionParcInformatique.Repository.FournisseurRepository;
import com.gestionParcInformatique.gestionParcInformatique.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository ProduitRepository;
    @Autowired
    private FournisseurRepository fournisseurRepository;

    private final String dafaultImage = "";

    public List<Produit> getAllProduits() {
        return ProduitRepository.findAll();
    }

    public Produit getProduitById(long idProduit) {
        Optional<Produit> optionalProduit = ProduitRepository.findById(idProduit);
        return optionalProduit.orElse(null);
    }

    public Produit addProduit(Produit produit) {
        if (produit.getImageURL() != null) {
            return ProduitRepository.save(produit);
        }
        produit.setImageURL(dafaultImage);
        return ProduitRepository.save(produit);
    }

    public Produit updateProduit(long idProduit, Produit Produit) {
        if (ProduitRepository.existsById(idProduit)) {
            Produit.setIdProduit(idProduit);
            return ProduitRepository.save(Produit);
        }
        return null;
    }

    public void deleteProduit(long idProduit) {
        ProduitRepository.deleteById(idProduit);
    }
}
