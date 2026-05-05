package com.objectville.model;



public class Road extends Cell implements IConnectable {
    public Road(int x, int y) {
        super(x, y, 'R');
    }

    @Override
    public boolean canConduct() {
        return true;
    }
}
