package com.gestionParcInformatique.gestionParcInformatique.Repository;

import com.gestionParcInformatique.gestionParcInformatique.Models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
//    public Optional<Utilisateur> findByImmatricule(String Immatricule);
}
