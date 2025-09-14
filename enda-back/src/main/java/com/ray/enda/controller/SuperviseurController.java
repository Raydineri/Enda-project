package com.ray.enda.controller;

import com.ray.enda.model.Superviseur;
import com.ray.enda.service.SuperviseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/superviseurs")
public class SuperviseurController {
    @Autowired
    private SuperviseurService superviseurService;

    @GetMapping
    public List<Superviseur> getAllSuperviseurs() {
        return superviseurService.getAllSuperviseurs();
    }

    @GetMapping("/{id}")
    public Optional<Superviseur> getSuperviseurById(@PathVariable Long id) {
        return superviseurService.getSuperviseurById(id);
    }

    @PostMapping
    public Superviseur createSuperviseur(@RequestBody Superviseur superviseur) {
        return superviseurService.saveSuperviseur(superviseur);
    }
}
