package com.gestionParcInformatique.gestionParcInformatique.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCategorie;
    @Column(unique=true, nullable=false)
    private String libelle;
    @Column(unique=true, nullable=false)
    private String abv;
    private LocalDate dateCreation;

    @PostPersist
    public void setDateCreationCategorie(){
        this.dateCreation = LocalDate.now();
    }

    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;

//    public  List<respMarque> getProduitsInfo(){
//        List<respMarque> info = new ArrayList<>();
//        for(Produit p : this.getProduits()){
//            respMarque m = respMarque.builder()
//                    .nomProduit(p.getNomProduit())
//                    .total(p.getTotalPiece())
//                    .marque(p.getMarque().getNomMarque())
//                    .build();
//            info.add(m);
//
//        }
//        return info;
//    }
}
