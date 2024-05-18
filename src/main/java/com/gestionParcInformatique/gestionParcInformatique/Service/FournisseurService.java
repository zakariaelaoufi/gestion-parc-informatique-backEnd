package com.gestionParcInformatique.gestionParcInformatique.Service;

import com.gestionParcInformatique.gestionParcInformatique.Models.Fournisseur;
import com.gestionParcInformatique.gestionParcInformatique.Repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService {
    private FournisseurRepository fournisseurRepository;
    @Autowired
    public FournisseurService(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    public Fournisseur createFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    public List<Fournisseur> getAllFournisseur() {
        return fournisseurRepository.findAll();
    }

    public Optional<Fournisseur> getFournisseur(long idFournisseur) {
        return fournisseurRepository.findById(idFournisseur);
    }

    public Fournisseur updateFournisseur(Fournisseur fournisseur,long idFournisseur) {
        Optional<Fournisseur> Originalfournisseur = fournisseurRepository.findById(idFournisseur);
        Originalfournisseur.get().setNomFournisseur(fournisseur.getNomFournisseur());
        Originalfournisseur.get().setICE(fournisseur.getICE());
        return fournisseurRepository.save(Originalfournisseur.get());
    }

    public String deleteFournisseur(long idFournisseur) {
        fournisseurRepository.deleteById(idFournisseur);
        return "deleted seuccessfully";
    }
}
