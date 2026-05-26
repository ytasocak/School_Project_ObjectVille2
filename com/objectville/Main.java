package com.objectville;

import com.objectville.infrastructure.*;
import com.objectville.model.base.Cell;
import com.objectville.model.zone.Zone;

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
         for(int currentTick=1; currentTick<=totalTicks;currentTick++){
            System.out.println("Tick :"+currentTick);

            for(int row=0; row<grid.length;row++){
                for(int col= 0;col<grid[0].length;col++){
                    Cell currentCell=grid[row][col];
                    if (currentCell instanceof Zone) {
                        Zone z = (Zone) currentCell;
                        z.resetTickData();
                    }
                }
            }
        }
    }
}
