package com.gestionParcInformatique.gestionParcInformatique.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Travailler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTravail;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @JsonIgnore
    private Utilisateur utilisateur;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    @ManyToOne
    @JoinColumn(name = "idEntiteTravail", referencedColumnName = "idEntiteTravail")
    @JsonIgnore
    private EntiteTravail entiteTravail;

    @OneToMany(mappedBy = "travailler")
    private List<Affecter> affecters;

    //Other info
    public String getImmatricule() {
        return this.getUtilisateur()!= null ? this.getUtilisateur().getImmatricule() : "";
    }

    public String getNomEntiteTravail() {
        return this.entiteTravail!=null ?
                this.entiteTravail.getTypeEntiteTravail() + " " + entiteTravail.getNomEntiteTravail()
                : "";
    }

    public long getIdEntiteTravail() {
        return this.entiteTravail!=null ?
                this.entiteTravail.getIdEntiteTravail()
                : -1;
    }

    public Type getTypeEntiteTravail() {
        return  this.entiteTravail!=null ?
                this.entiteTravail.getTypeEntiteTravail()
                : null;
    }
}