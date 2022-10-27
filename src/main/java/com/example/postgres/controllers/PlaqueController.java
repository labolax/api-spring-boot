package com.example.postgres.controllers;

import com.example.postgres.BO.entities.Plaque;
import com.example.postgres.services.PlaqueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlaqueController {
    private final PlaqueService plaqueService;

    public PlaqueController(PlaqueService plaqueService) {
        this.plaqueService = plaqueService;
    }

    @GetMapping("/createPlaque")
    public Plaque createPlaque(@RequestBody Plaque plaque){
        return plaqueService.createPlaque(plaque);
    }

}
