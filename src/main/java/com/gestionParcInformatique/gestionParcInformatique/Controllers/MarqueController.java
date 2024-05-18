package com.gestionParcInformatique.gestionParcInformatique.Controllers;

import com.gestionParcInformatique.gestionParcInformatique.Models.Marque;
import com.gestionParcInformatique.gestionParcInformatique.Service.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marques")
public class MarqueController {
    private final MarqueService marqueService;

    @Autowired
    public MarqueController(MarqueService marqueService) {
        this.marqueService = marqueService;
    }

    @GetMapping
    public List<Marque> getAllMarques() {
        return marqueService.getAllMarques();
    }

    @GetMapping("/{idMarque}")
    public Marque getMarqueById(@PathVariable("idMarque") long idMarque) {
        return marqueService.getMarqueById(idMarque);
    }

    @PostMapping
    public Marque addMarque(@RequestBody Marque marque) {
        return marqueService.addMarque(marque);
    }

    @PutMapping("/{idMarque}")
    public Marque updateMarque(@PathVariable("idMarque") long idMarque, @RequestBody Marque updatedMarque) {
        return marqueService.updateMarque(idMarque, updatedMarque);
    }

    @DeleteMapping("/{idMarque}")
    public void deleteMarque(@PathVariable("idMarque") long idMarque) {
        marqueService.deleteMarque(idMarque);
    }
}
