package com.gestionParcInformatique.gestionParcInformatique.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitResp {
    private long idProduit;
    private String nomProduit;
    private String imageURL;
    private int totalPiece;
    private int nbActif;
}
