package com.ray.enda.repository;

import com.ray.enda.model.AP;
import com.ray.enda.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APRepository extends JpaRepository<AP, String> {
    AP findByGsmAndZone(String apGsm, Zone zone);

}