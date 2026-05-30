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
        ServiceAreaManager serviceManager= new ServiceAreaManager();

        ConnectivityValidator connectivityValidator=new ConnectivityValidator(grid.length,grid[0].length);
        VisitedTracker visitedTracker= new VisitedTracker(grid.length,grid[0].length);
        UtilityFlowManager utilityManager= new UtilityFlowManager(connectivityValidator,visitedTracker);

        int totalPopulationPool=0;
        int totalGoodsPool=0;
        int totalLifeStylePool=0;

        for(int currentTick=1; currentTick<=totalTicks;currentTick++){
            System.out.println("Tick :"+currentTick);

            for(int row=0; row<grid.length;row++){
                for(int col=0;col<grid[0].length;col++){
                    Cell currentCell=grid[row][col];
                    if (currentCell instanceof Zone) {
                        Zone z = (Zone) currentCell;
                        z.resetTickData();
                    }
                }
            }
        serviceManager.applyServices(grid);

        utilityManager.processGrid(grid);

        int housingCount=0;
        int commercialCount=0;
        int industrialCount=0;
        for(int r=0; r<grid.length;r++){
            for(int c=0; c<grid[0].length;c++){
                if(grid[r][c] != null && grid[r][c].getSymbol()=='H'){
                    housingCount++;
                }else if(grid[r][c] != null && grid[r][c].getSymbol()=='C'){
                    commercialCount++;
                }else if(grid[r][c] != null && grid[r][c].getSymbol()=='I'){
                    industrialCount++;
                }
            }
        }
            int populationPerHouse;
            if(housingCount>0){
                populationPerHouse=totalPopulationPool/housingCount;
            }else{
                populationPerHouse=0;
            }

            int goodsPerCommercial;
            if(commercialCount>0){
                goodsPerCommercial=totalGoodsPool/commercialCount;
            }else{
                goodsPerCommercial=0;
            }

            int lifeStylePerIndustrial;
            if(industrialCount>0){
                lifeStylePerIndustrial=totalLifeStylePool/industrialCount;
            }else{
                lifeStylePerIndustrial=0;
            }
            for(int r=0;r<grid.length;r++){
                for(int c=0;c<grid[0].length;c++) {
                    Cell currentCell=grid[r][c];

                    if (currentCell instanceof Zone){
                        Zone currentZone =(Zone) currentCell;
                        if(currentCell.getSymbol()=='H'){
                            currentZone.receivePopulation(populationPerHouse);
                        }else if(currentCell.getSymbol()=='C'){
                            currentZone.receiveGoods(goodsPerCommercial);
                        }else if(currentCell.getSymbol()=='I'){
                            currentZone.receiveLifestyle(lifeStylePerIndustrial);
                        }
                        currentZone.updateZone();
                    }
                }
            }
        }
    }
}
