package com.objectville.infrastructure;

import com.objectville.exceptions.MapValidationException;
import com.objectville.model.base.Cell;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ResultWriter {

    public void writeResult(String filePath, Cell[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            throw new MapValidationException("Map path is correct but grid is empty");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            int n = grid.length;
            int m = grid[0].length;

            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {
                    char symbol = (grid[row][col] != null) ? grid[row][col].getSymbol() : '?';
                    bw.write(symbol);
                }
                bw.newLine();
            }

            System.out.println("Result printed to " + filePath);

        } catch (IOException e) {
            System.err.println("Error while writing the result: " + e.getMessage());
        }
    }
}