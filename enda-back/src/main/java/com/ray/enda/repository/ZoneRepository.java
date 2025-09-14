package com.ray.enda.repository;

import com.ray.enda.model.Superviseur;
import com.ray.enda.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Zone findByIdAndSuperviseur(Long zoneId, Superviseur superviseur);

}