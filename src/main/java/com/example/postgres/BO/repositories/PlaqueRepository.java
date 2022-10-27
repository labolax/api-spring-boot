package com.example.postgres.BO.repositories;

import com.example.postgres.BO.entities.Plaque;
import com.example.postgres.BO.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaqueRepository extends JpaRepository<Plaque,Integer> {
}
