package com.objectville.infrastructure;

import com.objectville.model.base.Cell;
import com.objectville.model.zone.Zone;

public class ServiceAreaManager {
    public void applyServices(Cell[][] grid) {
        // scan all cells to find service buildings
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == null)
                    continue;

                char sym = grid[r][c].getSymbol();

                // F=FireStation(Security), D=Doctor(Health), S=School(Education)
                if (sym == 'F' || sym == 'D' || sym == 'S') {
                    int range = 5; // default range for now

                    // apply effect to nearby zones
                    for (int targetR = 0; targetR < grid.length; targetR++) {
                        for (int targetC = 0; targetC < grid[targetR].length; targetC++) {
                            int distance = Math.abs(r - targetR) + Math.abs(c - targetC);

                            // check if inside manhattan distance
                            if (distance <= range) {
                                if (grid[targetR][targetC] instanceof Zone) {
                                    Zone targetZone = (Zone) grid[targetR][targetC];

                                    if (sym == 'F')
                                        targetZone.setSecurity(true);
                                    else if (sym == 'D')
                                        targetZone.setHealth(true);
                                    else if (sym == 'S')
                                        targetZone.setEducation(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
