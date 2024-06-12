    package com.gestionParcInformatique.gestionParcInformatique.Models;

    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.springframework.web.multipart.MultipartFile;

    import java.util.List;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class ProduitRequest {
        private Produit produit;
        private long idProduit;
        private Livraison livraison;
        private Fournisseur fournisseur;
        private Marque marque;
        private Categorie categorie;
        private int quantite;
        private List<String> numeroSerie;
    }
