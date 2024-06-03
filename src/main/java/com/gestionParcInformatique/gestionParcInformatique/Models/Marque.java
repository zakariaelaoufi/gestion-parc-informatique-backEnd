package com.gestionParcInformatique.gestionParcInformatique.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE marque SET deleted = true WHERE id_marque=?")
public class Marque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMarque;
    @Column(nullable = false)
    private String nomMarque;
    private boolean deleted = Boolean.FALSE;
    private LocalDate dateCreation;

    @PostPersist
    public void setDateCreationMarque(){
        this.dateCreation = LocalDate.now();
    }

    @OneToMany(mappedBy = "marque")
    private List<Produit> produits;

//    public  List<respMarque> getProduitsInfo(){
//        List<respMarque> info = new ArrayList<>();
//        for(Produit p : this.getProduits()){
//            respMarque m = respMarque.builder()
//                    .nomProduit(p.getNomProduit())
//                    .total(p.getTotalPiece())
//                    .libelle(p.getCategorie().getLibelle())
//                    .build();
//            info.add(m);
//        }
//        return info;
//    }


}
