package com.objectville.infrastructure;

import com.objectville.model.base.Cell;
import com.objectville.model.interfaces.IConnectable;

public class ConnectivityValidator {
    private static final int[] delta_Row = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private static final int[] delta_Column = { -1, 0, 1, -1, 1, -1, 0, 1 };
    private int rows, cols;

    public ConnectivityValidator(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public boolean isValid(int r, int c) {
        return r >= 0 && r < rows && c >= 0 && c < cols;
    }

    public boolean canFlow(Cell cell) {
        return cell != null && cell instanceof IConnectable;
    }

    public Cell[] getNeighbors(Cell[][] grid, int r, int c) {
        Cell[] temp = new Cell[8];
        int count = 0;
        for (int i = 0; i < 8; i++) {
            int nextR = r + delta_Row[i];
            int nextC = c + delta_Column[i];
            if (isValid(nextR, nextC) && canFlow(grid[nextR][nextC])) {
                temp[count++] = grid[nextR][nextC];
            }
        }
        Cell[] result = new Cell[count];
        System.arraycopy(temp, 0, result, 0, count);
        return result;
    }
}
