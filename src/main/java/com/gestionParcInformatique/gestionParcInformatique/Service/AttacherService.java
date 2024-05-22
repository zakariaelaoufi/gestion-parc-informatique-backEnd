package com.gestionParcInformatique.gestionParcInformatique.Service;

import com.gestionParcInformatique.gestionParcInformatique.Models.Affecter;
import com.gestionParcInformatique.gestionParcInformatique.Models.Attacher;
import com.gestionParcInformatique.gestionParcInformatique.Repository.AttacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttacherService {

    @Autowired
    private AttacherRepository attacherRepository;

    public List<Attacher> getAllAttachment() {
        return attacherRepository.findAll();
    }

    public List<Attacher> getAllNotAvailableAffectations() {
        return attacherRepository.findAllByDateRetoureNull();
    }

    public Optional<Attacher> findAttachmentByid(long idAttachment) {
        return attacherRepository.findById(idAttachment);
    }

    public Attacher addAttachment(Attacher attacher) {
        return attacherRepository.save(attacher);
    }

    public Attacher updateAttachment(long idAttachment, Attacher attachment) {
        if (attacherRepository.existsById(idAttachment)) {
            attachment.setIdAttachment(idAttachment);
            return attacherRepository.save(attachment);
        }
        return null;
    }

    public void deleteAttachement(long idAttachment) {
        attacherRepository.deleteById(idAttachment);
    }
}
