package com.objectville;

import com.objectville.infrastructure.*;
import com.objectville.model.base.Cell;

public class Main {
    public static void main(String[] args){
        String mapFilePath="map.txt";
        int totalTicks= 5;
        System.out.println("Starting ObjectVille simulation...");
        System.out.println("Map File Path: "+ mapFilePath);
        System.out.println("Total Ticks to Run: "+ totalTicks);
        System.out.println("----------------------------------------------");
        MapLoader mapLoader=new MapLoader();
        Cell[][] grid=null;
        try {
            grid= mapLoader.loadMap(mapFilePath);
            System.out.println("Map successfully loaded!");
            System.out.println("Map Dimensions: "+grid.length+"x"+grid[0].length);
        } catch (Exception e){
            System.err.println("Failed to load map: "+e.getMessage());
            return;
        }
        System.out.println("-----------------------------------------------");
    }
}