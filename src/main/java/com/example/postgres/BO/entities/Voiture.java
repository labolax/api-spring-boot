package com.example.postgres.BO.entities;

import javax.persistence.*;
import java.net.MalformedURLException;
import java.net.URL;

@Entity
public class Voiture {

    public Voiture() {}
    public Voiture(String color, int wheels, String location){
        this.color = color;
        this.wheels = wheels;
        this.location = location;
    }
    @Id
    @SequenceGenerator(name="VoitureIDSequence", sequenceName ="VoitureIDSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VoitureIDSequence")
    private int id;
    private String color;
    private int wheels;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private URL map;

    @Transient
    private String status = "functional";




    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public URL getMap() {return map;}

    public void setMap(URL map) {this.map = map;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWheels() {
        return wheels;
    }

    public void setWheels(int wheels) {
        this.wheels = wheels;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
