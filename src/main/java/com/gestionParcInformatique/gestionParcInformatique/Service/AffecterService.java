package com.gestionParcInformatique.gestionParcInformatique.Service;

import com.gestionParcInformatique.gestionParcInformatique.Models.Affecter;
import com.gestionParcInformatique.gestionParcInformatique.Repository.AffecterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AffecterService {
    @Autowired
    private AffecterRepository affecterRepository;

    public List<Affecter> getAllAffectations() {
        return affecterRepository.findAll();
    }

    public List<Affecter> getAllAvailableAffectations() {
        return affecterRepository.findAllByDateRetoureNull();
    }

    public Affecter getAffectationById(long idAffectation) {
        Optional<Affecter> optionalAffectation = affecterRepository.findById(idAffectation);
        return optionalAffectation.orElse(null);
    }

    public Affecter addAffectation(Affecter affectation) {
        return affecterRepository.save(affectation);
    }

    public Affecter updateAffectation(long idAffectation, Affecter affectation) {
        if (affecterRepository.existsById(idAffectation)) {
            affectation.setIdAffectation(idAffectation);
            return affecterRepository.save(affectation);
        }
        return null;
    }

    public void deleteAffectation(long idAffectation) {
        affecterRepository.deleteById(idAffectation);
    }
}
