package com.gestionParcInformatique.gestionParcInformatique.Repository;

import com.gestionParcInformatique.gestionParcInformatique.Models.Affecter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffecterRepository extends JpaRepository<Affecter, Long> {
    public List<Affecter> findAllByDateRetoureNull();
}
