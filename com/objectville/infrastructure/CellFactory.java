package com.objectville.infrastructure;

import com.objectville.exceptions.MapValidationException;
import com.objectville.model.base.Cell;
import com.objectville.model.base.EmptyCell;
import com.objectville.model.base.Road;
import com.objectville.model.zone.Commercial;
import com.objectville.model.zone.Housing;
import com.objectville.model.zone.Industrial;

public class CellFactory {
    public Cell createCell(char type, int row, int col){
        switch (Character.toUpperCase(type)) { // I saw Character.toUpperCase in online to convert char to upper
            case 'E': return new EmptyCell(row, col);
            case 'H': return new Housing(row, col,'H');
            case 'R': return new Road(row,col);
            case 'I': return new Industrial(row,col,'I'); // NULL AS A PLACEHOLDER
            case 'C': return new Commercial(row,col,'C');
            case 'P': return null;
            case 'W': return null;
            case 'T': return null;
            case 'F': return null;
            case 'D': return null;
            case 'S': return null;
            default:
                throw new MapValidationException("Unknown symbol at (" + row + "," + col + "): " + type);
    }
}
}
