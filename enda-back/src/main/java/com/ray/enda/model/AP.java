package com.ray.enda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
public class AP {
    @Id
    private String gsm;
    private String nom;
    private String prenom;
    private String email;

    @ManyToOne
    @JsonBackReference // To prevent infinite recursion
    private Zone zone;

    @OneToMany(mappedBy = "ap")
    @JsonManagedReference // To manage the serialization
    private Set<Recu> recus;

    // Getters and Setters

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Set<Recu> getRecus() {
        return recus;
    }

    public void setRecus(Set<Recu> recus) {
        this.recus = recus;
    }
}
