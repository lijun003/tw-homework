package com.ifox.domain;

public class AnimalLocation {

    private String animalId;
    private int xPrevious;
    private int yPrevious;
    private int xChange;
    private int yChange;

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public int getxPrevious() {
        return xPrevious;
    }

    public void setxPrevious(int xPrevious) {
        this.xPrevious = xPrevious;
    }

    public int getyPrevious() {
        return yPrevious;
    }

    public void setyPrevious(int yPrevious) {
        this.yPrevious = yPrevious;
    }

    public int getxChange() {
        return xChange;
    }

    public void setxChange(int xChange) {
        this.xChange = xChange;
    }

    public int getyChange() {
        return yChange;
    }

    public void setyChange(int yChange) {
        this.yChange = yChange;
    }



}
