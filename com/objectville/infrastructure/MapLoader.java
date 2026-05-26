package com.objectville.infrastructure;

import com.objectville.model.base.Cell;
import com.objectville.infrastructure.CellFactory;
import com.objectville.exceptions.MapValidationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {

    public Cell[][] loadMap(String fileName) {
        CellFactory cf = new CellFactory();
        List<String> lines = getStrings(fileName);

        int n = lines.size();
        int m = lines.get(0).length();

        Cell[][] grid = new Cell[n][m];

        for (int row = 0; row < n; row++) {
            String rowLine = lines.get(row);

            if (rowLine.length() != m) {
                throw new MapValidationException(
                        "Map dimensions do not match! Expected row length: " + m + ", Error at row: " + row
                );
            }

            for (int col = 0; col < m; col++) {
                char symbol = rowLine.charAt(col);
                grid[row][col] = cf.createCell(symbol, row, col);
            }
        }

        return grid;
    }

    private static List<String> getStrings(String fileName) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line.trim());
                }
            }
        } catch (IOException e) {
            throw new MapValidationException("File reading failed: " + fileName + " Why: " + e.getMessage());
        }

        if (lines.isEmpty()) {
            throw new MapValidationException("Map path is correct but file is empty?");
        }
        return lines;
    }
}