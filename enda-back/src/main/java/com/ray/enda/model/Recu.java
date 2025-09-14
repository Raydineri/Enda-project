package com.ray.enda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;

@Entity
public class Recu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_recudebut")
    private String numeroRecudebut;

    @Column(name = "numero_recufin")
    private String numeroRecufin;

    private String upload;

    @ManyToOne
    @JoinColumn(name = "ap_id", nullable = false)
    @JsonBackReference // To prevent infinite recursion
    private AP ap;


    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroRecudebut() {
        return numeroRecudebut;
    }

    public void setNumeroRecudebut(String numeroRecudebut) {
        this.numeroRecudebut = numeroRecudebut;
    }

    public String getNumeroRecufin() {
        return numeroRecufin;
    }

    public void setNumeroRecufin(String numeroRecufin) {
        this.numeroRecufin = numeroRecufin;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public AP getAp() {
        return ap;
    }

    public void setAp(AP ap) {
        this.ap = ap;
    }
}