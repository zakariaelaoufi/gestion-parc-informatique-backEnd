package com.gestionParcInformatique.gestionParcInformatique.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Attacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAttachment;
    @Column(nullable = false)
    private LocalDate dateAttachment;
    @Column(nullable = true)
    private LocalDate dateRetoure;

    @ManyToOne
    @JoinColumn(name = "idEntiteTravail",referencedColumnName = "idEntiteTravail")
    @JsonIgnore
    private EntiteTravail entiteTravail;
    @ManyToOne
    @JoinColumn(name = "idInventaire",referencedColumnName = "idInventaire")
    @JsonIgnore
    private Inventaire inventaire;

    public String getNomMachine() {
        return this.getInventaire() != null ?
                this.getInventaire().getNomProduit()
                : "";
    }

    public String getHostname() {
        return this.getInventaire() != null ?
                this.getInventaire().getHostname()
                : "";
    }

    public String getNumeroSerie() {
        return this.getInventaire() != null ?
                this.getInventaire().getNumeroSerie()
                : "";
    }

    public String getPlace() {
        return this.getEntiteTravail() != null ?
                this.getEntiteTravail().getTypeEntiteTravail() + " " + this.getEntiteTravail().getNomEntiteTravail()
                : "";
    }

    public long getIdEntiteTravail() {
        return this.getEntiteTravail() != null ?
                this.getEntiteTravail().getIdEntiteTravail()
                : -1;
    }

    public long getidInventaire() {
        return this.getInventaire().getIdInventaire();
    }
}
