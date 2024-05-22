package com.gestionParcInformatique.gestionParcInformatique.Controllers;


import com.gestionParcInformatique.gestionParcInformatique.Models.*;
import com.gestionParcInformatique.gestionParcInformatique.Service.AttacherService;
import com.gestionParcInformatique.gestionParcInformatique.Service.EntiteTravailService;
import com.gestionParcInformatique.gestionParcInformatique.Service.InventaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/attachments")
public class AttacherController {
    @Autowired
    private AttacherService attacherService;
    @Autowired
    private InventaireService inventaireService;
    @Autowired
    private EntiteTravailService travailService;

    @GetMapping
    public ResponseEntity<List<Attacher>> getAllAttachers() {
        List<Attacher> attachers = attacherService.getAllAttachment();
        return ResponseEntity.ok(attachers);
    }

    @GetMapping("/notavailable")
    public ResponseEntity<List<Attacher>> getAvailableAffectations() {
        List<Attacher> attachments = attacherService.getAllNotAvailableAffectations();
        return new ResponseEntity<>(attachments, HttpStatus.OK);
    }

    @GetMapping("/{idAttachment}")
    public ResponseEntity<Attacher> getAttacherById(@PathVariable long idAttachment) {
        Optional<Attacher> attacher = attacherService.findAttachmentByid(idAttachment);
        return ResponseEntity.ok(attacher.get());
    }

    @PostMapping
    public ResponseEntity<Attacher> createAttacher(@RequestBody Map<String, Object> attacherMap) {
        LocalDate dateAttachment = LocalDate.parse((String) attacherMap.get("dateAttachment"));
        long entiteTravailId = ((Map<String, Integer>) attacherMap.get("entiteTravail")).get("idEntiteTravail").longValue();
        long inventaireId = ((Map<String, Integer>) attacherMap.get("inventaire")).get("idInventaire").longValue();

        EntiteTravail entiteTravail = travailService.getEntityById(entiteTravailId);
        Inventaire inventaire = inventaireService.getInventaireById(inventaireId);

        Attacher attacher = Attacher.builder()
                .dateAttachment(dateAttachment)
                .entiteTravail(entiteTravail)
                .inventaire(inventaire)
                .build();

        inventaire.setEtat(Etat.ACTIF);
        inventaireService.updateInventaire(inventaire.getIdInventaire(),inventaire);

         Attacher savedAttacher = attacherService.addAttachment(attacher);
         return ResponseEntity.ok(savedAttacher);
    }


    @PutMapping("/{idAttachment}")
    public ResponseEntity<Attacher> updateAttachment(@PathVariable long idAttachment, @RequestBody Map<String, Object> attacherDetails) {
        Optional<Attacher> attachment = attacherService.findAttachmentByid(idAttachment);
        if (attachment.isPresent()) {
            LocalDate dateAttachment = LocalDate.parse((String) attacherDetails.get("dateAttachment"));
            LocalDate dateRetoure = LocalDate.parse((String) attacherDetails.get("dateRetoure"));
            long entiteTravailId = ((Map<String, Integer>) attacherDetails.get("entiteTravail")).get("idEntiteTravail").longValue();
            long inventaireId = ((Map<String, Integer>) attacherDetails.get("inventaire")).get("idInventaire").longValue();

            EntiteTravail entiteTravail = travailService.getEntityById(entiteTravailId);
            Inventaire inventaire = inventaireService.getInventaireById(inventaireId);

            Attacher attacher = Attacher.builder()
                    .idAttachment(idAttachment)
                    .dateAttachment(dateAttachment)
                    .dateRetoure(dateRetoure)
                    .entiteTravail(entiteTravail)
                    .inventaire(inventaire)
                    .build();

            Attacher updatedAttacher = attacherService.addAttachment(attacher);

            inventaire.setEtat(Etat.ENSTOCK);
            inventaireService.updateInventaire(inventaire.getIdInventaire(),inventaire);
            return ResponseEntity.ok(updatedAttacher);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idAttachment}")
    public ResponseEntity<Void> deleteAttacher(@PathVariable long idAttachment) {
        attacherService.deleteAttachement(idAttachment);
        return ResponseEntity.noContent().build();
    }
}
