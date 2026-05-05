package com.objectville.model;

import com.objectville.*;

public class CellFactory {
    public Cell CellFactory(char type,int x, int y){
        switch (Character.toUpperCase(type)) { // I saw Character.toUpperCase in online to convert char to upper
            case 'E':
                return new EmptyCell(x, y);
            case 'H':
                return new Housing(x, y,'H');
            case 'R':
                return new Road(x,y);
            default:
                throw new RuntimeException("Unknown symbol: "+type); // I will create an exception for mapValidation
    }
}
}
