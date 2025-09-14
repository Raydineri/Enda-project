package com.ray.enda.controller;

import com.ray.enda.model.Recu;
import com.ray.enda.repository.RecuRepository;
import com.ray.enda.service.RecuService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/recus")
@CrossOrigin(origins = "http://localhost:4200")
public class RecuController {

    private final RecuService recuService;
    private final JavaMailSender mailSender;
    private final RecuRepository recuRepository;

    @Autowired
    public RecuController(RecuService recuService, JavaMailSender mailSender, RecuRepository recuRepository) {
        this.recuService = recuService;
        this.mailSender = mailSender;
        this.recuRepository = recuRepository;
    }

    @PostMapping
    public ResponseEntity<Object> createRecu(
            @RequestParam("superviseurId") Long superviseurId,
            @RequestParam("zoneId") Long zoneId,
            @RequestParam("apGsm") String apGsm,
            @RequestParam("numeroRecudebut") String numeroRecudebut,
            @RequestParam("numeroRecufin") String numeroRecufin,
            @RequestParam("upload") MultipartFile upload
    ) {
        try {
            // Créer le recu en utilisant le service RecuService
            Recu recu = recuService.createRecu(superviseurId, zoneId, apGsm, numeroRecudebut, numeroRecufin, upload);

            // Retourner une réponse OK avec le recu créé
            return ResponseEntity.ok(recu);
        } catch (IllegalArgumentException e) {
            // Retourner une réponse BadRequest si une validation échoue
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IOException e) {
            // Retourner une réponse InternalServerError si une erreur d'I/O se produit
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (Exception e) {
            // Retourner une réponse InternalServerError pour toutes les autres exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    public void sendEmail(Recu recu) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo("raydineri07@gmail.com");  // Remplacez par l'adresse e-mail du destinataire
        helper.setSubject("Nouveau Reçu Créé");
        helper.setText("Un nouveau reçu a été créé. Détails: " + recu.toString());

        mailSender.send(mimeMessage);
    }
}