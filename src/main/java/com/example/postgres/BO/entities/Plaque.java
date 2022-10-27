package com.example.postgres.BO.entities;

import javax.persistence.*;

@Entity
public class Plaque {
    @Id
    @SequenceGenerator(name="PlaqueIDSequence", sequenceName ="PlaqueIDSequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PlaqueIDSequence")
    private int id;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private String postcode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
