package com.gestionParcInformatique.gestionParcInformatique.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "produit", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"hostname"})
})
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduit;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String nomProduit;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private double prix;
    @Column(nullable = false)
    private LocalDate dateLivraison;
    @Column(nullable = false)
    private int delai;
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "idFournisseur", referencedColumnName = "idFournisseur")
    private Fournisseur fournisseur;

    @ManyToOne
    @JoinColumn(name = "idMarque", referencedColumnName = "idMarque")
    @JsonIgnore
    private Marque marque;

    @ManyToOne
    @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
    @JsonIgnore
    private Categorie categorie;

    @OneToMany(mappedBy = "produit")
    private List<Inventaire> inventaireList;

    public int getTotalPiece() {
        int nb = 0;
        if (this.inventaireList != null) {
            for (Inventaire i : inventaireList) {
                nb++;
            }
        }
        return nb;
    }

    public LocalDate getDateExperation() {
        return dateLivraison.plusYears(delai);
    }

    private int StockDataByState(Etat state) {
        if (inventaireList == null) {
            return 0;
        }
        return (int) inventaireList.stream()
                .filter(inventaire -> inventaire.getEtat() == state)
                .count();
    }

    public Map<String, Integer> getNbEtatInv() {
        Map<String, Integer> data = new HashMap<>();
        data.put("ENSTOCK", StockDataByState(Etat.ENSTOCK));
        data.put("ACTIF", StockDataByState(Etat.ACTIF));
        data.put("INACTIF", StockDataByState(Etat.INACTIF));
        data.put("ENREPARATION", StockDataByState(Etat.ENREPARATION));
        data.put("MAINTENANCE", StockDataByState(Etat.MAINTENANCE));

        return data;
    }
}
