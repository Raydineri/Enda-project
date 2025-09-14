package com.ray.enda.service;

import com.ray.enda.model.Superviseur;
import com.ray.enda.repository.SuperviseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SuperviseurService {
    @Autowired
    private SuperviseurRepository superviseurRepository;

    public List<Superviseur> getAllSuperviseurs() {
        return superviseurRepository.findAll();
    }

    public Optional<Superviseur> getSuperviseurById(Long id) {
        return superviseurRepository.findById(id);
    }

    public Superviseur saveSuperviseur(Superviseur superviseur) {
        return superviseurRepository.save(superviseur);
    }
}
