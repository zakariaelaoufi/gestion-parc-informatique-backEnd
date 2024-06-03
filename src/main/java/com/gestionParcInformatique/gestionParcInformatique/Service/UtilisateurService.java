package com.gestionParcInformatique.gestionParcInformatique.Service;

import com.gestionParcInformatique.gestionParcInformatique.Models.Utilisateur;
import com.gestionParcInformatique.gestionParcInformatique.Repository.UtilisateurRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public int getTotalUtilisateurs() {
        return utilisateurRepository.countAllByDeletedIsFalse();
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
        return utilisateur;
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> getUtilisateur(long idUtilisateur) {
        return utilisateurRepository.findById(idUtilisateur);
    }

//    public Optional<Utilisateur> getUtilisateurByImmatricule(String Immatricule) {
//        return utilisateurRepository.findByImmatricule(Immatricule);
//    }

    public Utilisateur updtaeUtilisteur(Utilisateur utilisateur, long idUtilisateur) {
        Optional<Utilisateur> user = utilisateurRepository.findById(idUtilisateur);
        user.get().setImmatricule(utilisateur.getImmatricule());
        user.get().setNomUtilisateur(utilisateur.getNomUtilisateur());
        user.get().setPrenomUtilisateur(utilisateur.getPrenomUtilisateur());
        user.get().setEmail(utilisateur.getEmail());
        utilisateurRepository.save(user.get());
        return user.get();
    }

    public String deleteUtilisateur(long idUtilisateur) throws Exception {
        utilisateurRepository.deleteById(idUtilisateur);
        try {
            return "The user is deleted successfully";
        } catch (Exception e) {
            throw new Exception("The user did not exist.");
        }
    }
}
