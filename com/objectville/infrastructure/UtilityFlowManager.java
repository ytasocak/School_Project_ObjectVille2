package com.objectville.infrastructure;

import java.util.*;
import com.objectville.model.Cell;
import com.objectville.model.UtilityHub;

public class UtilityFlowManager {
    private ConnectivityValidator validator;
    private VisitedTracker tracker;

    public UtilityFlowManager(ConnectivityValidator validator, VisitedTracker tracker) {
        this.validator = validator;
        this.tracker = tracker;
    }

    private void runBFS(Cell[][] grid, UtilityHub startHub) {
        Queue<Cell> queue = new LinkedList<>();
        tracker.reset();
        queue.add(startHub);
        tracker.markVisited(startHub.getX(), startHub.getY());
    }

    public void processGrid(Cell[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] instanceof UtilityHub) {
                    runBFS(grid, (UtilityHub) grid[r][c]);
                }
            }
        }
    }

}
