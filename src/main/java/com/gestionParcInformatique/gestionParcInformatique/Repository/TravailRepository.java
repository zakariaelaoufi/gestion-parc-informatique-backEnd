package com.gestionParcInformatique.gestionParcInformatique.Repository;

import com.gestionParcInformatique.gestionParcInformatique.Models.Travailler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravailRepository extends JpaRepository<Travailler, Long> {
    List<Travailler> findAllByUtilisateurIdUtilisateurOrderByIdTravailAsc(long utilisateurId);
}
