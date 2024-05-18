package com.gestionParcInformatique.gestionParcInformatique.Service;

import com.gestionParcInformatique.gestionParcInformatique.Models.Travailler;
import com.gestionParcInformatique.gestionParcInformatique.Repository.TravailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravailService {
    @Autowired
    private TravailRepository travaillerRepository;

    public List<Travailler> getAllTravaux() {
        return travaillerRepository.findAll();
    }

    public List<Travailler> getAllTravauxByIdUtilisateur(long id) {
        return travaillerRepository.findAllByUtilisateurIdUtilisateurOrderByIdTravailAsc(id);
    }


    public Travailler getTravailById(long id) {
        Optional<Travailler> optionalTravail = travaillerRepository.findById(id);
        return optionalTravail.orElse(null);
    }

    public Travailler addTravail(Travailler travail) {
        return travaillerRepository.save(travail);
    }

    public Travailler updateTravail(Travailler travail) {
        if (travaillerRepository.existsById(travail.getIdTravail())) {
            return travaillerRepository.save(travail);
        }
        return null;
    }

    public void deleteTravail(long id) {
        travaillerRepository.deleteById(id);
    }
}
