package com.gestionParcInformatique.gestionParcInformatique.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Affecter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAffectation;
    @Column(nullable = false)
    private LocalDate dateAffectation;
    @Column(nullable = true)
    private LocalDate dateRetoure;

    @ManyToOne
    @JoinColumn(name = "idTravail",referencedColumnName = "idTravail")
    @JsonIgnore
    private Travailler travailler;
    @ManyToOne
    @JoinColumn(name = "idInventaire",referencedColumnName = "idInventaire")
    @JsonIgnore
    private Inventaire inventaire;

    // other return

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
    public String getImmatricule() {
        return this.getTravailler().getUtilisateur() != null ?
                this.getTravailler().getUtilisateur().getImmatricule()
                : "";
    }
    public String getPlace() {
        return this.getTravailler().getEntiteTravail() != null ?
                this.getTravailler().getEntiteTravail().getTypeEntiteTravail() + " " + this.getTravailler().getEntiteTravail().getNomEntiteTravail()
                : "";
    }



    public long getidTravail() {
        return this.getTravailler().getEntiteTravail() != null ?
                this.getTravailler().getIdTravail()
                : -1;
    }

    public long getidInventaire() {
        return this.getInventaire().getIdInventaire();
    }
}
