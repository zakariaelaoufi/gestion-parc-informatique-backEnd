package com.gestionParcInformatique.gestionParcInformatique.Repository;

import com.gestionParcInformatique.gestionParcInformatique.Models.EntiteTravail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntiteTravailRepository extends JpaRepository<EntiteTravail, Long> {
    public int countAllByDeletedIsFalse();
}
