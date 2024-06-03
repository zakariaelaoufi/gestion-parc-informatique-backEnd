package com.gestionParcInformatique.gestionParcInformatique.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionParcInformatique.gestionParcInformatique.Models.Categorie;
import com.gestionParcInformatique.gestionParcInformatique.Models.Produit;
import com.gestionParcInformatique.gestionParcInformatique.Repository.FournisseurRepository;
import com.gestionParcInformatique.gestionParcInformatique.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository ProduitRepository;
    @Autowired
    private FournisseurRepository fournisseurRepository;
    @Autowired
    private FileStorageService fileStorageService;

    private final String defaultImage = "default.jpg";

    public long totalProduit() {
        return ProduitRepository.count();
    }

    public List<Produit> findAllByCategorie(Categorie categorie) {
        return ProduitRepository.findAllByCategorie(categorie);
    }

    public List<Produit> getAllProduits() {
        return ProduitRepository.findAll();
    }

    public Produit getProduitById(long idProduit) {
        Optional<Produit> optionalProduit = ProduitRepository.findById(idProduit);
        return optionalProduit.orElse(null);
    }

    public Produit addProduit(Produit produit, MultipartFile file) {
        String imageURL;
        if (file.isEmpty() || file.getOriginalFilename() == null || file.getOriginalFilename().trim().isEmpty()) {
            imageURL = defaultImage;
        }
        else {
            imageURL = fileStorageService.storeFile(file);
        }
        produit.setImageURL(imageURL);
        return ProduitRepository.save(produit);
    }

    public Produit updateProduit(long idProduit, Produit produit, MultipartFile file) {
        if (ProduitRepository.existsById(idProduit)) {
            Optional<Produit> existingProduit = ProduitRepository.findById(idProduit);
            String originalFilename = file.getOriginalFilename();
             if (produit.getImageURL().equals(originalFilename)) {
                produit.setIdProduit(idProduit);
                return ProduitRepository.save(produit);
            }
            String imageURL = getImageURL(file);
            produit.setImageURL(imageURL);
            produit.setIdProduit(idProduit);
            return ProduitRepository.save(produit);
        }
        return null;
    }

    public void deleteProduit(long idProduit) {
        ProduitRepository.deleteById(idProduit);
    }

    private String getImageURL(MultipartFile file) {
        if (file.isEmpty() || file.getOriginalFilename() == null || file.getOriginalFilename().trim().isEmpty()) {
            return defaultImage;
        } else {
            return fileStorageService.storeFile(file);
        }
    }
}
