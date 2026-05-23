package com.objectville.infrastructure;

import com.objectville.model.base.Cell;
import com.objectville.infrastructure.CellFactory;
import com.objectville.exceptions.MapValidationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapLoader {

    public Cell[][] loadMap(String fileName) {

        CellFactory cf = new CellFactory();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            if (line == null) {
                throw new MapValidationException("Map path is correct but file is empty?");
            }

            int n = Integer.parseInt(line.trim());
            Cell[][] grid = new Cell[n][n];

            for (int row = 0; row < n; row++) {
                String rowLine = br.readLine();

                if (rowLine == null || rowLine.trim().length() != n) {
                    throw new MapValidationException(
                            "Map dimensions do not match!!!! Expected length: " + n + ", Error at row: " + row
                    );
                }

                String trimmedRow = rowLine.trim();
                for (int col = 0; col < n; col++) {
                    char symbol = trimmedRow.charAt(col);
                    grid[row][col] = cf.createCell(symbol,row,col);
                }
            }

            return grid;

        } catch (IOException e) {
            throw new MapValidationException("File reading failed: " + fileName + " Why: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new MapValidationException("Map size (N) is not a valid integer.");
        }
    }
}