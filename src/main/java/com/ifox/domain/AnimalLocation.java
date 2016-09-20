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

    public int getXPrevious() {
        return xPrevious;
    }

    public void setXPrevious(int xPrevious) {
        this.xPrevious = xPrevious;
    }

    public int getYPrevious() {
        return yPrevious;
    }

    public void setYPrevious(int yPrevious) {
        this.yPrevious = yPrevious;
    }

    public int getXChange() {
        return xChange;
    }

    public void setXChange(int xChange) {
        this.xChange = xChange;
    }

    public int getYChange() {
        return yChange;
    }

    public void setYChange(int yChange) {
        this.yChange = yChange;
    }
}
