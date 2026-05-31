package com.objectville.infrastructure;

import com.objectville.exceptions.MapValidationException;
import com.objectville.model.base.Cell;
import com.objectville.model.base.EmptyCell;
import com.objectville.model.base.Road;
import com.objectville.model.utility.InternetHub;
import com.objectville.model.utility.PowerPlant;
import com.objectville.model.utility.WaterStation;
import com.objectville.model.zone.Commercial;
import com.objectville.model.zone.Housing;
import com.objectville.model.zone.Industrial;
import com.objectville.model.zone.Hospital;
import com.objectville.model.zone.PoliceStation;
import com.objectville.model.zone.School;

public class CellFactory {
    public Cell createCell(char type, int row, int col) {
        switch (Character.toUpperCase(type)) { // Character.toUpperCase convert char to upper saw in online
            case 'E': return new EmptyCell(row, col);
            case 'H': return new Housing(row, col, 'H');
            case 'R': return new Road(row, col);
            case 'I': return new Industrial(row, col, 'I');
            case 'C': return new Commercial(row, col, 'C');
            case 'P': return new PowerPlant(row, col);
            case 'W': return new WaterStation(row, col);
            case 'T': return new InternetHub(row, col);
            case 'F': return new PoliceStation(row, col);
            case 'D': return new Hospital(row, col);
            case 'S': return new School(row, col);
            default:
                throw new MapValidationException("Unknown symbol at (" + row + "," + col + "): " + type);
        }
    }
}
