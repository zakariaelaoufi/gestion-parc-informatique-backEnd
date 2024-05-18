package com.gestionParcInformatique.gestionParcInformatique.Repository;

import com.gestionParcInformatique.gestionParcInformatique.Models.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur,Long> {
}
