package com.objectville.infrastructure;

import com.objectville.model.base.Cell;
import com.objectville.model.zone.Zone;
import com.objectville.model.base.ServiceBuilding;

public class ServiceAreaManager {
    private ResultWriter resultWriter;

    public void setResultWriter(ResultWriter resultWriter) {
        this.resultWriter = resultWriter;
    }

    private void logMessage(String message) {
        if (resultWriter != null) {
            resultWriter.log(message);
        }
    }

    private String getZoneName(char symbol) {
        if (symbol == 'H') return "House";
        if (symbol == 'C') return "Commercial";
        if (symbol == 'I') return "Industrial";
        return "Zone";
    }

    public void applyServices(Cell[][] grid) {
        // scan all cells to find service buildings
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == null)
                    continue;

                char sym = grid[r][c].getSymbol();

                // F=FireStation(Security), D=Doctor(Health), S=School(Education)
                if (sym == 'F' || sym == 'D' || sym == 'S') {
                    int range = ((ServiceBuilding) grid[r][c]).getRadius();

                    // apply effect to nearby zones
                    for (int targetR = 0; targetR < grid.length; targetR++) {
                        for (int targetC = 0; targetC < grid[targetR].length; targetC++) {
                            double distance = Math.sqrt(Math.pow(r - targetR, 2) + Math.pow(c - targetC, 2));

                            // check if inside range using Euclidean distance
                            if (distance <= range) {
                                if (grid[targetR][targetC] instanceof Zone) {
                                    Zone targetZone = (Zone) grid[targetR][targetC];

                                    if (sym == 'F') {
                                        targetZone.setSecurity(true);
                                        logMessage(getZoneName(targetZone.getSymbol()) + " at (" + targetR + "," + targetC + ") received security service");
                                    } else if (sym == 'D' && targetZone.getSymbol() == 'H') {
                                        targetZone.setHealth(true);
                                        logMessage(getZoneName(targetZone.getSymbol()) + " at (" + targetR + "," + targetC + ") received health service");
                                    } else if (sym == 'S' && targetZone.getSymbol() == 'H') {
                                        targetZone.setEducation(true);
                                        logMessage(getZoneName(targetZone.getSymbol()) + " at (" + targetR + "," + targetC + ") received education service");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
