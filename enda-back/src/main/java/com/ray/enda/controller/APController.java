package com.ray.enda.controller;

import com.ray.enda.model.AP;
import com.ray.enda.service.APService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aps")
public class APController {
    @Autowired
    private APService apService;

    @GetMapping
    public List<AP> getAllAPs() {
        return apService.getAllAPs();
    }

    @GetMapping("/{gsm}")
    public Optional<AP> getAPByGsm(@PathVariable String gsm) {
        return apService.getAPByGsm(gsm);
    }

    @PostMapping
    public AP createAP(@RequestBody AP ap) {
        return apService.saveAP(ap);
    }
}
