package com.ifox.domain;

public class AnimalLocation {
    private String anmimalId;
    private int xPrevious;
    private int yPrevious;
    private int xChange;
    private int yChange;

    public String getAnmimalId() {
        return anmimalId;
    }

    public void setAnmimalId(String anmimalId) {
        this.anmimalId = anmimalId;
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
