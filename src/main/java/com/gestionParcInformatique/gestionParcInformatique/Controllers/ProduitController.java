package com.gestionParcInformatique.gestionParcInformatique.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionParcInformatique.gestionParcInformatique.Models.*;
import com.gestionParcInformatique.gestionParcInformatique.Service.FileStorageService;
import com.gestionParcInformatique.gestionParcInformatique.Service.InventaireService;
import com.gestionParcInformatique.gestionParcInformatique.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitService ProduitService;
    @Autowired
    private InventaireService inventaireService;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> Produits = ProduitService.getAllProduits();
        return new ResponseEntity<>(Produits, HttpStatus.OK);
    }

    @GetMapping("/{idProduit}")
    public ResponseEntity<Produit> getProduitById(@PathVariable("idProduit") long idProduit) {
        Produit Produit = ProduitService.getProduitById(idProduit);
        return new ResponseEntity<>(Produit, HttpStatus.OK);
    }

//    @PostMapping("/upload/image")
//    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
//        String imageURL;
//        if (file.isEmpty() || file.getOriginalFilename() == null || file.getOriginalFilename().trim().isEmpty()) {
//            imageURL = defaultImage;
//        } else {
//            imageURL = fileStorageService.storeFile(file);
//        }
//        System.out.println(imageURL);
//        return new ResponseEntity<>(imageURL, HttpStatus.OK);
//    }

    @GetMapping("/image/{imageURL}")
    public ResponseEntity<Resource> getImage(@PathVariable("imageURL") String imageURL) throws IOException  {
        Path imagePath = fileStorageService.getFileStorageLocation(imageURL);

        UrlResource resource = new UrlResource(imagePath.toUri());
        if (!resource.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MediaType contentType = MediaType.IMAGE_JPEG;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType);

        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    @PostMapping
    public ResponseEntity<?> addProduit(@RequestParam("data") String data, @RequestParam("file") MultipartFile file) throws JsonProcessingException {
        if (data.isEmpty()) return null;
        ProduitRequest produitRequest = jacksonObjectMapper.readValue(data, ProduitRequest.class);

        Produit produit = produitRequest.getProduit();
        produit.setFournisseur(produitRequest.getFournisseur());
        produit.setMarque( produitRequest.getMarque());
        produit.setCategorie(produitRequest.getCategorie());

        Produit newProduit = ProduitService.addProduit(produit,file);
        int quantite = produitRequest.getQuantite();
        List<Inventaire> inventaires = new ArrayList<>(quantite);
        for (int i=0;i<quantite;i++) {
            inventaires.add(Inventaire.builder()
                    .produit(newProduit)
                    .etat(Etat.ENSTOCK)
                    .numeroSerie(produitRequest.getNumeroSerie().get(i))
                    .build());
        }
        List<Inventaire> savedInventaires = inventaireService.addAllInventaires(inventaires);
        return new ResponseEntity<>(newProduit, HttpStatus.CREATED);
    }

    @PutMapping("/{idProduit}")
    public ResponseEntity<Produit> updateProduit(@PathVariable("idProduit") long idProduit, @RequestParam("data") String data, @RequestParam("file") MultipartFile file) throws IOException{
        System.out.println(data);
        System.out.println(file.getOriginalFilename());
        ProduitRequest produitRequest = jacksonObjectMapper.readValue(data, ProduitRequest.class);
        Produit produit = produitRequest.getProduit();
        produit.setFournisseur(produitRequest.getFournisseur());
        produit.setMarque( produitRequest.getMarque());
        produit.setCategorie(produitRequest.getCategorie());
        Produit updatedProduit = ProduitService.updateProduit(idProduit, produit, file);
        return new ResponseEntity<>(updatedProduit, HttpStatus.OK);
    }

    @DeleteMapping("/{idProduit}")
    public ResponseEntity<Void> deleteProduit(@PathVariable("idProduit") long idProduit) {
        ProduitService.deleteProduit(idProduit);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
