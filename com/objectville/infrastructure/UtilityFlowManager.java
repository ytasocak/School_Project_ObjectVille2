package com.objectville.infrastructure;

import java.util.*;
import com.objectville.model.base.Cell;
import com.objectville.model.interfaces.IPowerable;
import com.objectville.model.interfaces.IWaterable;
import com.objectville.model.utility.UtilityHub;

public class UtilityFlowManager {
    private ConnectivityValidator validator;
    private VisitedTracker tracker;

    public UtilityFlowManager(ConnectivityValidator validator, VisitedTracker tracker) {
        this.validator = validator;
        this.tracker = tracker;
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
                    // Check hub type and deliver utility accordingly
                    if (startHub.getSymbol() == 'P' && neighbor instanceof IPowerable) {
                        ((IPowerable) neighbor).receiveElectricity(1);
                        startHub.consumeCapacity(1);
                    } else if (startHub.getSymbol() == 'W' && neighbor instanceof IWaterable) {
                        ((IWaterable) neighbor).receiveWater(1);
                        startHub.consumeCapacity(1);
                    }
                    
                    // Mark visited and add to queue to continue flow
                    tracker.markVisited(neighbor.getX(), neighbor.getY());
                    queue.add(neighbor);
                }
            }
        }

    }

    public void processGrid(Cell[][] grid) {
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
