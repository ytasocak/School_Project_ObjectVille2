package com.objectville.infrastructure;

import java.util.*;
import com.objectville.model.base.Cell;
import com.objectville.model.interfaces.IPowerable;
import com.objectville.model.interfaces.IWaterable;
import com.objectville.model.utility.UtilityHub;
import com.objectville.model.zone.Zone;

public class UtilityFlowManager {
    private ConnectivityValidator validator;
    private VisitedTracker tracker;
    private ResultWriter resultWriter;

    public UtilityFlowManager(ConnectivityValidator validator, VisitedTracker tracker) {
        this.validator = validator;
        this.tracker = tracker;
    }

    // Set result writer for logging
    public void setResultWriter(ResultWriter resultWriter) {
        this.resultWriter = resultWriter;
    }

    // Write log
    private void logMessage(String message) {
        if (resultWriter != null) {
            resultWriter.log(message);
        }
    }

    // Helper to get formatted zone name
    private String getZoneName(char symbol) {
        if (symbol == 'H')
            return "House";
        if (symbol == 'C')
            return "Commercial";
        if (symbol == 'I')
            return "Industrial";
        return "Zone";
    }

    private void runBFS(Cell[][] grid, UtilityHub startHub) {
        // Start BFS to spread power/water through the grid
        Queue<Cell> queue = new LinkedList<>();
        tracker.reset();
        queue.add(startHub);
        tracker.markVisited(startHub.getX(), startHub.getY());

        while (!queue.isEmpty()) {
            // Stop if hub capacity is exhausted (100 zones limit)
            if (startHub.getAvailableCapacity() <= 0) {
                break;
            }
            Cell current = queue.poll();

            Cell[] neighbors = validator.getNeighbors(grid, current.getX(), current.getY());

            for (Cell neighbor : neighbors) {
                if (neighbor != null && !tracker.isVisited(neighbor.getX(), neighbor.getY())) {
                    if (startHub.getAvailableCapacity() <= 0) {
                        break;
                    }

                    // Distribute utility based on demand
                    if (neighbor instanceof Zone) {
                        Zone zoneNeighbor = (Zone) neighbor;
                        int demand = zoneNeighbor.getUtilityDemand();
                        String zName = getZoneName(zoneNeighbor.getSymbol());

                        if (startHub.getSymbol() == 'P') {
                            int needed = Math.max(0, demand - zoneNeighbor.getElectricityReceived());
                            int absorb = Math.min(needed, startHub.getAvailableCapacity());
                            if (absorb > 0) {
                                zoneNeighbor.receiveElectricity(absorb);
                                startHub.consumeCapacity(absorb);
                                logMessage(zName + " at (" + neighbor.getX() + "," + neighbor.getY() + ") received "
                                        + absorb + " electricity");
                            }
                        } else if (startHub.getSymbol() == 'W') {
                            int needed = Math.max(0, demand - zoneNeighbor.getWaterReceived());
                            int absorb = Math.min(needed, startHub.getAvailableCapacity());
                            if (absorb > 0) {
                                zoneNeighbor.receiveWater(absorb);
                                startHub.consumeCapacity(absorb);
                                logMessage(zName + " at (" + neighbor.getX() + "," + neighbor.getY() + ") received "
                                        + absorb + " water");
                            }
                        } else if (startHub.getSymbol() == 'T' && (zoneNeighbor.getSymbol() == 'H' || zoneNeighbor.getSymbol() == 'C')) {
                            int needed = Math.max(0, demand - zoneNeighbor.getInternetReceived());
                            int absorb = Math.min(needed, startHub.getAvailableCapacity());
                            if (absorb > 0) {
                                zoneNeighbor.receiveInternet(absorb);
                                startHub.consumeCapacity(absorb);
                                logMessage(zName + " at (" + neighbor.getX() + "," + neighbor.getY() + ") received "
                                        + absorb + " internet");
                            }
                        }
                    }

                    // Mark visited and add to queue to continue flow
                    tracker.markVisited(neighbor.getX(), neighbor.getY());
                    queue.add(neighbor);
                }
            }
        }

    }

    public void processGrid(Cell[][] grid) {
        // Reset usage of all hubs before starting distribution in this tick
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] instanceof UtilityHub) {
                    ((UtilityHub) grid[r][c]).resetUsage();
                }
            }
        }

        // Scan map for hubs and trigger distribution
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] instanceof UtilityHub) {
                    runBFS(grid, (UtilityHub) grid[r][c]);
                }
            }
        }
    }

}
