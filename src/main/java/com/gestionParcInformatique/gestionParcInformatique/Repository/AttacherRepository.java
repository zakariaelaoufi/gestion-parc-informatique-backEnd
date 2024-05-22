package com.gestionParcInformatique.gestionParcInformatique.Repository;

import com.gestionParcInformatique.gestionParcInformatique.Models.Affecter;
import com.gestionParcInformatique.gestionParcInformatique.Models.Attacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttacherRepository extends JpaRepository<Attacher,Long> {
    public List<Attacher> findAllByDateRetoureNull();

}
