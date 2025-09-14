package com.ray.enda.controller;

import com.ray.enda.model.Zone;
import com.ray.enda.service.ZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {
    @Autowired
    private ZoneService zoneService;

    @GetMapping
    public List<Zone> getAllZones() {
        return zoneService.getAllZones();
    }

    @GetMapping("/{id}")
    public Optional<Zone> getZoneById(@PathVariable Long id) {
        return zoneService.getZoneById(id);
    }
    //implimenter getZoneByIdAndSuperviseur


    @PostMapping
    public Zone createZone(@RequestBody Zone zone) {
        return zoneService.saveZone(zone);
    }
}
