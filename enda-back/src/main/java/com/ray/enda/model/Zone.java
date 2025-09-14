package com.ray.enda.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.Set;
@Entity
public class Zone {
    @Id
    private Long id;
    private String name;

    @ManyToOne
    @JsonBackReference // To prevent infinite recursion
    private Superviseur superviseur;

    @OneToMany(mappedBy = "zone")
    @JsonManagedReference // To manage the serialization
    private Set<AP> aps;
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Superviseur getSuperviseur() {
        return superviseur;
    }

    public void setSuperviseur(Superviseur superviseur) {
        this.superviseur = superviseur;
    }

    public Set<AP> getAps() {
        return aps;
    }

    public void setAps(Set<AP> aps) {
        this.aps = aps;
    }
}
