package com.gestionParcInformatique.gestionParcInformatique.Repository;

import com.gestionParcInformatique.gestionParcInformatique.Models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Long> {
}
