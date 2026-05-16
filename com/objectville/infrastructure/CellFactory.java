package com.objectville.infrastructure;

import com.objectville.exceptions.MapValidationException;
import com.objectville.model.base.Cell;
import com.objectville.model.base.EmptyCell;
import com.objectville.model.base.Road;
import com.objectville.model.zone.Housing;

public class CellFactory {
    public Cell createCell(char type, int x, int y){
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
