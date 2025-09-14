package com.ray.enda.service;

import com.ray.enda.model.AP;
import com.ray.enda.model.Recu;
import com.ray.enda.model.Superviseur;
import com.ray.enda.model.Zone;
import com.ray.enda.repository.RecuRepository;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class RecuService {

    private final RecuRepository recuRepository;
    private final SuperviseurService superviseurService;
    private final ZoneService zoneService;
    private final APService apService;
    private final EmailService emailService;

    @Autowired
    public RecuService(RecuRepository recuRepository, SuperviseurService superviseurService,
                       ZoneService zoneService, APService apService, EmailService emailService) {
        this.recuRepository = recuRepository;
        this.superviseurService = superviseurService;
        this.zoneService = zoneService;
        this.apService = apService;
        this.emailService = emailService;
    }

    @Transactional
    public Recu createRecu(Long superviseurId, Long zoneId, String apGsm, String numeroRecudebut,
                           String numeroRecufin, MultipartFile upload) throws IOException, MessagingException {
        Superviseur superviseur = superviseurService.getSuperviseurById(superviseurId)
                .orElseThrow(() -> new IllegalArgumentException("Superviseur non trouvé avec l'ID " + superviseurId));

        Zone zone = Optional.ofNullable(zoneService.getZoneByIdAndSuperviseur(zoneId, superviseur))
                .orElseThrow(() -> new IllegalArgumentException("Zone non trouvée avec l'ID " + zoneId + " pour le superviseur " + superviseurId));

        AP ap = Optional.ofNullable(apService.getApByGsmAndZone(apGsm, zone))
                .orElseThrow(() -> new IllegalArgumentException("AP non trouvé avec le GSM " + apGsm + " pour la zone " + zone.getName()));

        Recu recu = new Recu();
        recu.setNumeroRecudebut(numeroRecudebut);
        recu.setNumeroRecufin(numeroRecufin);

        try {
            String uploadDir = "uploads/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filename = upload.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);
            Files.copy(upload.getInputStream(), filePath);

            recu.setUpload(filePath.toString());
        } catch (IOException e) {
            throw new IOException("Erreur lors de la sauvegarde du fichier uploadé : " + e.getMessage(), e);
        }

        recu.setAp(ap);

        Recu savedRecu = recuRepository.save(recu);

        // Envoyer un email à l'AP
        String subject = "Nouveaux Reçus validés";
        String text = "<p>Cher " + ap.getPrenom() + " " + ap.getNom() + ",</p>" +
              "<p>Les reçus ont été validés avec succès avec les informations suivantes :</p>" +"<br>"+
              "<ul>" +"<br>"+
              "<li><strong>Numéro de Reçu Début :</strong> " + numeroRecudebut + "</li>" +"<br>"+
              "<li><strong>Numéro de Reçu Fin :</strong> " + numeroRecufin + "</li>" +
              "</ul>" +"<br>"+
              "<p>Merci,</p>" +"<br>"+
              "<p>L'équipe Enda</p>";
        emailService.sendEmail(ap.getEmail(), subject, text, "benothmaraen@gmail.com"); // Remplacez par l'adresse email de l'expéditeur

        return savedRecu;
    }
}