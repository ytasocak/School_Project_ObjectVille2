package com.objectville.infrastructure;

import com.objectville.model.Cell;
import com.objectville.model.Zone;

public class ServiceAreaManager {
    public void applyServices(Cell[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] instanceof ServiceBuilding) {
                    int range = ((ServiceBuilding) grid[r][c]).getRange();
                    for (int targetR = 0; targetR < grid.length; targetR++) {
                        for (int targetC = 0; targetC < grid[targetR].length; targetC++) {
                            int distance = Math.abs(r - targetR) + Math.abs(c - targetC);
                            if (distance <= range) {
                                if (grid[targetR][targetC] instanceof Zone) {
                                    char symbol = grid[r][c].getSymbol();
                                    if (symbol == 'F') {
                                        ((Zone) grid[targetR][targetC]).setSecurity(true);
                                    } else if (symbol == 'D') {
                                        ((Zone) grid[targetR][targetC]).setHealth(true);
                                    } else if (symbol == 'S') {
                                        ((Zone) grid[targetR][targetC]).setEducation(true);
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
