package com.ray.enda.service;

import com.ray.enda.model.AP;
import com.ray.enda.model.Zone;
import com.ray.enda.repository.APRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class APService {
    @Autowired
    private APRepository apRepository;

    public List<AP> getAllAPs() {
        return apRepository.findAll();
    }

    public Optional<AP> getAPByGsm(String gsm) {
        return apRepository.findById(gsm);
    }

    public AP saveAP(AP ap) {
        return apRepository.save(ap);
    }
    public APService(APRepository apRepository) {
        this.apRepository = apRepository;
    }

    public AP getApByGsmAndZone(String apGsm, Zone zone) {
        return apRepository.findByGsmAndZone(apGsm, zone);
    }

}
