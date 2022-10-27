package com.example.postgres.BO.repositories;

import com.example.postgres.BO.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoitureRepository extends JpaRepository<Voiture,Integer> {
    List<Voiture> findAllByColorOrderById(String color);
    List<Voiture> findAllByOrderById();

}
