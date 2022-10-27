package com.example.postgres.controllers;

import com.example.postgres.BO.entities.Voiture;
import com.example.postgres.services.PlaqueService;
import com.example.postgres.services.VoitureService;
import com.what3words.javawrapper.What3WordsV3;
import com.what3words.javawrapper.response.Autosuggest;
import com.what3words.javawrapper.response.ConvertToCoordinates;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class VoitureController {

    private final What3WordsV3 api = new What3WordsV3("DSC4SN4O");

    private final VoitureService voitureService;

    public VoitureController(VoitureService voitureService, PlaqueService plaqueService) {
        this.voitureService = voitureService;
    }


    @GetMapping("/createAVoiture")
    @ResponseStatus(HttpStatus.CREATED)
    public Voiture addVoiture(){
        return voitureService.createVoiture();
    }

    @PostMapping("/createMyVoiture")
    @ResponseStatus(HttpStatus.CREATED)
    public Voiture addVoiture(@RequestBody Voiture voiture){
        return voitureService.createVoiture(voiture);
    }

    @GetMapping("/getAllVoiture")
    public List<Voiture> getAllVoiture(){
        return voitureService.getAllVoiture();
    }
    @GetMapping("/findVoituresByColor")
    public List<Voiture> findVoituresByColor(@RequestParam String color){
        return voitureService.findVoituresByColor(color);
    }
    @GetMapping("/findById")
    public Voiture findVoitureById(@RequestParam int id){return voitureService.findVoitureById(id);}

    @DeleteMapping("/deleteById")
    public Voiture deleteVoiture(@RequestParam int id){
        return voitureService.deleteById(id);
    }

    @GetMapping("/findVoitureLocation")
    public ConvertToCoordinates findVoitureLocation(@RequestParam int id){
        return voitureService.findVoitureLocation(id);
    }

    @PutMapping("/moveMyVoiture")
    public Voiture moveMyVoiture(@RequestBody Voiture voiture){
        return voitureService.moveMyVoiture(voiture);
    }

    @GetMapping("/test")
    public Object test(@RequestParam String location){
        Autosuggest autosuggest = api.autosuggest(location)
                .clipToCountry("FR")
                .execute();
        return autosuggest.getSuggestions().get(0);
    }

}
