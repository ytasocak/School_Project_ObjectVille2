package com.objectville.infrastructure;

public class VisitedTracker {
    private boolean[][] visited;
    private int rows, cols;

    public VisitedTracker(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.visited = new boolean[rows][cols];
    }

    public void markVisited(int r, int c) {
        visited[r][c] = true;
    }

    public boolean isVisited(int r, int c) {
        return visited[r][c];
    }

    public void reset() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                visited[r][c] = false;
            }
        }
    }
}
