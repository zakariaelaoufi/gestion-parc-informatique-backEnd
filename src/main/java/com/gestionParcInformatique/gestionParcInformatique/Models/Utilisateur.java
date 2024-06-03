package com.gestionParcInformatique.gestionParcInformatique.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "utilisateur", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"immatricule"}),
        @UniqueConstraint(columnNames = {"email"})
})
@SQLDelete(sql = "UPDATE utilisateur SET deleted = true WHERE id_utilisateur=?")
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUtilisateur;
    @Column(nullable = false)
    private String Immatricule;
    @Column(nullable = false)
    private String nomUtilisateur;
    @Column(nullable = false)
    private String prenomUtilisateur;
    @Column(nullable = false)
    private String email;
    private boolean deleted = Boolean.FALSE;



    @OneToMany(mappedBy = "utilisateur")
    private List<Travailler> travaillers;

    public long getIdTravail() {
        return (this.getTravaillers() != null && !this.getTravaillers().isEmpty()) ? this.getTravaillers().get(0).getIdTravail()
                : -1;
    }

    @PreRemove
    private void preRemove() {
        travaillers.forEach(travailler -> {
            travailler.setDateFin(LocalDate.now());
        });
    }
}
