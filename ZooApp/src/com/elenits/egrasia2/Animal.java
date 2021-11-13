package com.elenits.egrasia2;

import java.io.Serializable;

public class Animal implements Serializable {
    int id;
    String name;
    String species;
    int weight;
    int maxAge;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    //constructor
    public Animal(int id, String name, String species, int weight, int maxAge) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.weight = weight;
        this.maxAge = maxAge;
    }
}
