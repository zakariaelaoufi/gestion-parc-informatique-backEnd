package com.gestionParcInformatique.gestionParcInformatique.Repository;

import com.gestionParcInformatique.gestionParcInformatique.Models.Etat;
import com.gestionParcInformatique.gestionParcInformatique.Models.Inventaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventaireRepository extends JpaRepository<Inventaire, Long> {
    public List<Inventaire> findAllByEtatIsLike(Etat etat);
    public long count();
}
