package com.gestionParcInformatique.gestionParcInformatique.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE entite_travail SET deleted = true WHERE id_entite_travail=?")
@Where(clause = "deleted=false")
public class EntiteTravail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idEntiteTravail;
    private String nomEntiteTravail;
    @Enumerated(EnumType.STRING)
    private Type typeEntiteTravail;
    private boolean deleted = Boolean.FALSE;


    @ManyToOne
    @JoinColumn(name = "ParentId", referencedColumnName = "idEntiteTravail",nullable = true)
    private EntiteTravail parent;

    @OneToMany(mappedBy = "entiteTravail")
    private List<Travailler> travaillerList;

    @OneToMany(mappedBy = "entiteTravail")
    private List<Attacher> attacherList;
}
