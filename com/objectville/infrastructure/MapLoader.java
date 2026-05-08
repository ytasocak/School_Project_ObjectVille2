package com.objectville.infrastructure;
import com.objectville.model.Cell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapLoader {
    public Cell[][] loadMap(String fileName){
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String line = br.readLine();
            line = line.trim();
            int n = Integer.parseInt(line);

            Cell[][] grid = new Cell[n][n];
            for (int row = 0; row < n; row++) {
                String rowLine = br.readLine();
                for (int column = 0; column < n; column++) {
                    char symbol = rowLine.charAt(column);
                }
            }
        }catch (IOException e){
            System.out.println("IO Exception occurred");
        }

        return null;// placeholder
    }
}
