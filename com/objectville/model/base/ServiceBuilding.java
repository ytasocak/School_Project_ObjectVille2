package com.objectville.model.base;

public class ServiceBuilding extends Cell{
    private int radius;

    public ServiceBuilding(int x, int y, char symbol, int radius) {
        super(x, y, symbol);
        this.radius=radius;
    }
    public int getRadius(){
        return radius;
    }
}
