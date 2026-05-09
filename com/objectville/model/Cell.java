package com.objectville.model;

// Basic Cell Class
public abstract class Cell {
    private int x;
    private int y;
    private char symbol;

    public Cell(int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getSymbol() {
        return symbol;
    }
}
