package com.example.postgres.services;

import com.example.postgres.BO.entities.Plaque;
import com.example.postgres.BO.entities.Voiture;
import com.example.postgres.BO.repositories.PlaqueRepository;
import org.springframework.stereotype.Service;

@Service
public class PlaqueService {
    private final PlaqueRepository plaqueRepository;


    public PlaqueService(PlaqueRepository plaqueRepository) {
        this.plaqueRepository = plaqueRepository;
    }

    public Plaque createPlaque(Plaque plaque){
        plaqueRepository.save(plaque);
        return plaque;


    }
}
