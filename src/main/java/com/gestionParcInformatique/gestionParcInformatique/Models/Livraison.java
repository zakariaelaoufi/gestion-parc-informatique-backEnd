package com.gestionParcInformatique.gestionParcInformatique.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idLivraison;
    @Column(nullable = false)
    private LocalDate dateLivraison;
    @Column(nullable = false)
    private int delai;
    @Column(nullable = false)
    private double prix;

    @OneToMany(mappedBy = "livraison")
    @JsonIgnore
    private List<Inventaire> inventaireList;

    @ManyToOne
    @JoinColumn(name = "idFournisseur", referencedColumnName = "idFournisseur")
    private Fournisseur fournisseur;

    public LocalDate getDateExperation() {
        return dateLivraison.plusYears(delai);
    }

}
