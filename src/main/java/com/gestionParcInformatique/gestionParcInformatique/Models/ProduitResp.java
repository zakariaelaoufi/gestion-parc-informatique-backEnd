package com.gestionParcInformatique.gestionParcInformatique.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.io.Resource;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitResp {
    private Produit produit;
    private Resource image;
}
