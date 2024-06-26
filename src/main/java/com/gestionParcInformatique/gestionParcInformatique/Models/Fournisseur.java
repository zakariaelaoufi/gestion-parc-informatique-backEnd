package com.gestionParcInformatique.gestionParcInformatique.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Fournisseur", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ICE"})
})
@SQLDelete(sql = "UPDATE fournisseur SET deleted = true WHERE id_fournisseur=?")
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idFournisseur;
    @Column(nullable = false)
    private String nomFournisseur;
    @Column(nullable = false)
    private String ICE;
    @Column(nullable = false)
    private String Tel;
    @Column(nullable = false)
    private String Fax;
    @Column(nullable = false)
    private String Adresse;
    @Column(nullable = false)
    private String Email;
    private boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "fournisseur")
    @JsonIgnore
    private List<Produit> produits;
}
