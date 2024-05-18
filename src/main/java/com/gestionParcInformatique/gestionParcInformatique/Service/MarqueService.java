package com.gestionParcInformatique.gestionParcInformatique.Service;

import com.gestionParcInformatique.gestionParcInformatique.Models.Marque;
import com.gestionParcInformatique.gestionParcInformatique.Repository.MarqueRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MarqueService {
    private final MarqueRepository marqueRepository;

    public List<Marque> getAllMarques() {
        return marqueRepository.findAll();
    }

    public Marque getMarqueById(long idMarque) {
        return marqueRepository.findById(idMarque).orElse(null);
    }

    public Marque addMarque(Marque marque) {
        return marqueRepository.save(marque);
    }

    public Marque updateMarque(long idMarque, Marque updatedMarque) {
        if (marqueRepository.existsById(idMarque)) {
            updatedMarque.setIdMarque(idMarque);
            return marqueRepository.save(updatedMarque);
        }
        return null;
    }

    public void deleteMarque(long idMarque) {
        marqueRepository.deleteById(idMarque);
    }
}
