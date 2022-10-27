package com.example.postgres.services;

import com.example.postgres.BO.entities.Voiture;
import com.example.postgres.BO.repositories.VoitureRepository;
import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.response.ConvertToCoordinates;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class VoitureService {

    private final VoitureRepository voitureRepository;

    private final What3WordsV3 api = new What3WordsV3("DSC4SN4O");

    public VoitureService(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
    }

    private void saveUrl(Voiture voiture, String spec) {
        try {
            voiture.setMap( new URL ("https://what3words.com/"+spec));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        voiture.setLocation(spec);
        voitureRepository.save(voiture);

    }

    private void apiCheckLocation(String location) {
        if( ! api.convertToCoordinates(location).execute().isSuccessful()) throw
                new ResponseStatusException(HttpStatus.NOT_FOUND, "The location gives in argument can't be find.");
    }

    public Voiture createVoiture() {
        Voiture voiture = new Voiture();
        voiture.setColor("red");
        voiture.setWheels(4);
        saveUrl(voiture, "puzzled.riper.topple");
        return voiture;
    }

    public Voiture moveMyVoiture(int id, String location) {
        Voiture voiture = findVoitureById(id);
        apiCheckLocation(location);
        saveUrl(voiture, location);
        return voiture;
    }

    public Voiture createVoiture(Voiture voiture){
        Voiture newVoiture = new Voiture(voiture.getColor(), voiture.getWheels(), voiture.getLocation());
        apiCheckLocation(newVoiture.getLocation());
        saveUrl(newVoiture, newVoiture.getLocation());
        return newVoiture;
    }

    public List<Voiture> getAllVoiture(){
        if(voitureRepository.findAll().isEmpty()) throw
            new ResponseStatusException(HttpStatus.NOT_FOUND, "No voitures in database");
        return voitureRepository.findAllByOrderById();
    }

    public Voiture findVoitureById(int id){
        return voitureRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found with id : " + id));
    }


    public List<Voiture> findVoituresByColor(String color){
        if(voitureRepository.findAllByColorOrderById(color).isEmpty()) throw
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No voitures found with color : " + color);
        return voitureRepository.findAllByColorOrderById(color);
    }

    public Voiture deleteById(int id){
        Voiture voiture = findVoitureById(id);
        voitureRepository.deleteById(id);
        voiture.setStatus("deleted");
        return voiture;
    }

    public ConvertToCoordinates findVoitureLocation(int id){
        return api.convertToCoordinates(findVoitureById(id).getLocation()).execute();
    }

}
