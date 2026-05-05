package com.objectville.model;

import com.objectville.Exceptions.MapValidationException;

public class CellFactory {
    public Cell CellFactory(char type,int x, int y){
        switch (Character.toUpperCase(type)) { // I saw Character.toUpperCase in online to convert char to upper
            case 'E': return new EmptyCell(x, y);
            case 'H': return new Housing(x, y,'H');
            case 'R': return new Road(x,y);
            case 'I': return null; // NULL AS A PLACEHOLDER
            case 'C': return null;
            case 'P': return null;
            case 'W': return null;
            case 'T': return null;
            case 'F': return null;
            case 'D': return null;
            case 'S': return null;
            default:
                throw new MapValidationException("Unknown symbol at (" + x + "," + y + "): " + type);
    }
}
}
