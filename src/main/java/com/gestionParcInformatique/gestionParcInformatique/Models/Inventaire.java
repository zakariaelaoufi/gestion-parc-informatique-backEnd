package com.gestionParcInformatique.gestionParcInformatique.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gestionParcInformatique.gestionParcInformatique.Repository.InventaireRepository;
import com.gestionParcInformatique.gestionParcInformatique.Service.InventaireService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@SQLDelete(sql = "UPDATE inventaire SET etat = 'REFORME' WHERE id_inventaire=?")
public class Inventaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idInventaire;
    @Column(nullable = true, unique = true)
    private String numeroSerie;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Etat etat;
    @Column(nullable = true)
    private String hostname;

    @ManyToOne
    @JoinColumn(name = "idProduit", referencedColumnName = "idProduit")
    @JsonIgnore
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "idLivraison",referencedColumnName = "idLivraison")
    @JsonIgnore
    private Livraison livraison;

    @OneToMany(mappedBy = "inventaire")
    private List<Affecter> affecters;
    @OneToMany(mappedBy = "inventaire")
    private List<Attacher> attachers;


    @PostPersist
    public void generateAndSetHostname() {
        if (hostname == null) {
            this.setHostname(String.format("%05d", this.idInventaire));
        }
    }

    public String getHostname() {
        return  this.getProduit().getCategorie().getAbv() + "." + String.format("%05d", this.idInventaire);
    }

    // Other return
    public String getNomProduit() {
        return this.getProduit()!=null ?
                this.getProduit().getNomProduit()
                : "";
    }

    public String getMarque() {
        return this.getProduit().getMarque()!=null ?
                this.getProduit().getMarque().getNomMarque()
                : "";
    }
    public String getCategorie() {
        return this.getProduit().getCategorie() != null ?
                this.getProduit().getCategorie().getLibelle()
                : "";
    }
//    public String getHostname() {
//        String categoryPrefix = switch (this.produit.getCategorie().getLibelle()) {
//            case "PC" -> "UC.";
//            case "Imprimante" -> "IM.";
//            default -> "";
//        };
//        return categoryPrefix+String.format("%05d", this.getIdInventaire());
//    }
    public String getAffectationPlace() {
        if(this.affecters!=null  && !this.affecters.isEmpty())
            if (this.getEtat() == Etat.ENSTOCK || this.getEtat() == Etat.ENREPARATION ) {
                return "";
            } else {
                return this.affecters.getLast().getTravailler().getNomEntiteTravail();
            }
        return null;
    }

    public String getAttavhementPlace() {
        if(this.attachers!=null  && !this.attachers.isEmpty())
            if (this.getEtat() == Etat.ENSTOCK || this.getEtat() == Etat.ENREPARATION ) {
                return "";
            } else {
                return this.getAttachers().getFirst().getPlace();
            }
        return null;
    }

    public String getAffectationPersonne() {
        if (this.affecters != null && !this.affecters.isEmpty()) {
            if (this.getEtat() == Etat.ENSTOCK || this.getEtat() == Etat.ENREPARATION ) {
                return "";
            } else {
                return this.affecters.getLast().getTravailler().getUtilisateur().getNomUtilisateur() +
                        " " +
                        this.affecters.getLast().getTravailler().getUtilisateur().getPrenomUtilisateur();
            }
        }
        return null;
    }
    public long getLastIdAffectation() {
        return (this.affecters!=null  && !this.affecters.isEmpty()) ?
                this.getAffecters().getFirst().getIdAffectation()
                : -1;
    }

    public long getIdProduit() {
        return this.getProduit().getIdProduit();
    }

    public String getIce() {
        return this.getLivraison() != null ?
                this.getLivraison().getFournisseur().getICE()
                : "";
    }
}
