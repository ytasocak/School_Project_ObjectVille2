package com.objectville.infrastructure;

import com.objectville.model.Cell;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ResultWriter {
    public void writeResult(String filePath, Cell[][] grid){
        try(PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
        } catch (IOException e) {
            System.err.println("Error while writing the result: " + e.getMessage());
        }
    }
}
