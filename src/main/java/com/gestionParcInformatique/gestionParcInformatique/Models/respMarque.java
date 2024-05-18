package com.gestionParcInformatique.gestionParcInformatique.Models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class respMarque {
    private  String nomProduit;
    private  int total;
    private String marque;
    private String libelle;
}
