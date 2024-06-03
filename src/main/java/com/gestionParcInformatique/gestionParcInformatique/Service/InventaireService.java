package com.gestionParcInformatique.gestionParcInformatique.Service;

import com.gestionParcInformatique.gestionParcInformatique.Models.Etat;
import com.gestionParcInformatique.gestionParcInformatique.Models.Inventaire;
import com.gestionParcInformatique.gestionParcInformatique.Repository.InventaireRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventaireService {
    @Autowired
    private InventaireRepository inventaireRepository;

    public long totalInventaire() {
        return inventaireRepository.count();
    }

    public List<Inventaire> getAllInventaire() {
        return inventaireRepository.findAll();
    }

    public List<Inventaire> getAllInventaireEnstock() {
        return inventaireRepository.findAllByEtatIsLike(Etat.ENSTOCK);
    }

    public Inventaire getInventaireById(long idItem) {
        Optional<Inventaire> optionalItem = inventaireRepository.findById(idItem);
        return optionalItem.orElse(null);
    }

    public Inventaire addInventaire(Inventaire item) {
        return inventaireRepository.save(item);
    }

    public Inventaire updateInventaire(long idInventaire, Inventaire inventaire) {
        if (inventaireRepository.existsById(idInventaire)) {
            inventaire.setIdInventaire(idInventaire);
            return inventaireRepository.save(inventaire);
        }
        return null;
    }

    @Transactional
    public List<Inventaire> addAllInventaires(List<Inventaire> inventaires) {
        List<Inventaire> savedInventaires = inventaireRepository.saveAll(inventaires);
        return savedInventaires;
    }

    public void deleteInventaire(long idInventaire) {
        inventaireRepository.deleteById(idInventaire);
    }
}
