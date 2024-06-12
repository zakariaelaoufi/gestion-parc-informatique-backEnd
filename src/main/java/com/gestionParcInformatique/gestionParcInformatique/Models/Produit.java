package com.gestionParcInformatique.gestionParcInformatique.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

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
    private String imageURL;

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


    public Set<Livraison> getLivraisonList() {
        if (this.inventaireList == null) {
            return Collections.emptySet();
        }
        return this.inventaireList.stream()
                .map(Inventaire::getLivraison)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public int getTotalPiece() {
        int nb = 0;
        if (this.inventaireList != null) {
            for (Inventaire i : inventaireList) {
                nb++;
            }
        }
        return nb;
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
        data.put("REFORME", StockDataByState(Etat.REFORME));
        data.put("ENREPARATION", StockDataByState(Etat.ENREPARATION));

        return data;
    }

    public long getMarqueID() {
        return this.getMarque().getIdMarque();
    }

    public String getNomMarque() {
        return this.getMarque().getNomMarque();
    }

    public long getCatrgorieID() {
        return this.getCategorie().getIdCategorie();
    }

    public String getLibelle() {
        return this.getCategorie().getLibelle();
    }
}
