package com.ifox.domain;

import java.util.List;

public class Data {
    private String id;
    private String time;
    private List<AnimalLocation> animalLocations;

    public String getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public List<AnimalLocation> getAnimalLocations() {
        return animalLocations;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAnimalLocations(List<AnimalLocation> animalLocations) {
        this.animalLocations = animalLocations;
    }

    public void setId(String id) {
        this.id = id;

    }
}
