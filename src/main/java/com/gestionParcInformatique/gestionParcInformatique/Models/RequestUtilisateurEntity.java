package com.gestionParcInformatique.gestionParcInformatique.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestUtilisateurEntity {
    private Utilisateur utilisateur;
    private EntiteTravail entiteTravail;
    private LocalDate dateDebut;
}
