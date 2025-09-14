package com.ray.enda.service;

import com.ray.enda.model.Superviseur;
import com.ray.enda.model.Zone;
import com.ray.enda.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ZoneService {
    @Autowired
    private ZoneRepository zoneRepository;

    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    public Optional<Zone> getZoneById(Long id) {
        return zoneRepository.findById(id);
    }

    public Zone saveZone(Zone zone) {
        return zoneRepository.save(zone);
    }
    public ZoneService(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    public Zone getZoneByIdAndSuperviseur(Long zoneId, Superviseur superviseur) {
        return zoneRepository.findByIdAndSuperviseur(zoneId, superviseur);
    }


}
