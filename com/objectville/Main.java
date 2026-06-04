package com.objectville;

import com.objectville.infrastructure.*;
import com.objectville.model.base.Cell;
import com.objectville.model.zone.Zone;

public class Main {
    private static String getZoneName(char symbol) {
        if (symbol == 'H') return "House";
        if (symbol == 'C') return "Commercial";
        if (symbol == 'I') return "Industrial";
        return "Zone";
    }

    public static void main(String[] args){
        String mapFilePath = "map.txt";
        int totalTicks = 5;

        if (args.length > 0) {
            mapFilePath = args[0];
        }
        if (args.length > 1) {
            try {
                totalTicks = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
            }
        }

        MapLoader mapLoader = new MapLoader();
        Cell[][] grid = null;
        try {
            grid = mapLoader.loadMap(mapFilePath);
        } catch (Exception e){
            System.err.println("Failed to load map: " + e.getMessage());
            return;
        }

        ResultWriter.clearFile("output.txt");
        ResultWriter writer = new ResultWriter();

        ServiceAreaManager serviceManager = new ServiceAreaManager();
        serviceManager.setResultWriter(writer);

        ConnectivityValidator connectivityValidator = new ConnectivityValidator(grid.length, grid[0].length);
        VisitedTracker visitedTracker = new VisitedTracker(grid.length, grid[0].length);
        UtilityFlowManager utilityManager = new UtilityFlowManager(connectivityValidator, visitedTracker);
        utilityManager.setResultWriter(writer);

        int totalPopulationPool = 0;
        int totalGoodsPool = 0;
        int totalLifeStylePool = 0;

        for(int currentTick = 1; currentTick <= totalTicks; currentTick++){
            writer.log("Tick " + currentTick);

            for(int row = 0; row < grid.length; row++){
                for(int col = 0; col < grid[0].length; col++){
                    Cell currentCell = grid[row][col];
                    if (currentCell instanceof Zone) {
                        Zone z = (Zone) currentCell;
                        z.resetTickData();
                    }
                }
            }

            serviceManager.applyServices(grid);

            utilityManager.processGrid(grid);

            int housingCount = 0;
            int commercialCount = 0;
            int industrialCount = 0;
            for(int r = 0; r < grid.length; r++){
                for(int c = 0; c < grid[0].length; c++){
                    if(grid[r][c] != null) {
                        char sym = grid[r][c].getSymbol();
                        if(sym == 'H') housingCount++;
                        else if(sym == 'C') commercialCount++;
                        else if(sym == 'I') industrialCount++;
                    }
                }
            }

            int lifestylePerHouse = housingCount > 0 ? totalLifeStylePool / housingCount : 0;
            int goodsPerCommercial = commercialCount > 0 ? totalGoodsPool / commercialCount : 0;
            int populationPerWorker = (industrialCount + commercialCount) > 0 ? totalPopulationPool / (industrialCount + commercialCount) : 0;

            for(int r = 0; r < grid.length; r++){
                for(int c = 0; c < grid[0].length; c++) {
                    Cell currentCell = grid[r][c];
                    if (currentCell instanceof Zone){
                        Zone currentZone = (Zone) currentCell;
                        if(currentCell.getSymbol() == 'H'){
                            currentZone.receiveLifestyle(lifestylePerHouse);
                            if (lifestylePerHouse > 0) {
                                writer.log("House at (" + r + "," + c + ") received " + lifestylePerHouse + " lifestyle");
                            }
                        }else if(currentCell.getSymbol() == 'I'){
                            currentZone.receivePopulation(populationPerWorker);
                            if (populationPerWorker > 0) {
                                writer.log("Industrial at (" + r + "," + c + ") received " + populationPerWorker + " population");
                            }
                        }else if(currentCell.getSymbol() == 'C'){
                            currentZone.receivePopulation(populationPerWorker);
                            if (populationPerWorker > 0) {
                                writer.log("Commercial at (" + r + "," + c + ") received " + populationPerWorker + " population");
                            }
                            currentZone.receiveGoods(goodsPerCommercial);
                            if (goodsPerCommercial > 0) {
                                writer.log("Commercial at (" + r + "," + c + ") received " + goodsPerCommercial + " goods");
                            }
                        }
                    }
                }
            }

            for(int r = 0; r < grid.length; r++){
                for(int c = 0; c < grid[0].length; c++) {
                    Cell currentCell = grid[r][c];
                    if (currentCell instanceof Zone){
                        Zone currentZone = (Zone) currentCell;
                        int oldLevel = currentZone.getLevel();
                        currentZone.updateZone();

                        String typeStr = "";
                        if (currentCell.getSymbol() == 'H') typeStr = "population";
                        else if (currentCell.getSymbol() == 'C') typeStr = "lifestyle";
                        else if (currentCell.getSymbol() == 'I') typeStr = "goods";

                        writer.log(getZoneName(currentCell.getSymbol()) + " at (" + r + "," + c + ") generated " + currentZone.getOutput() + " " + typeStr);

                        int newLevel = currentZone.getLevel();
                        if (newLevel > oldLevel) {
                            writer.log(getZoneName(currentCell.getSymbol()) + " at (" + r + "," + c + ") levels up from " + oldLevel + " to " + newLevel);
                        } else if (newLevel < oldLevel) {
                            writer.log(getZoneName(currentCell.getSymbol()) + " at (" + r + "," + c + ") levels down from " + oldLevel + " to " + newLevel);
                        }
                    }
                }
            }

            totalPopulationPool = 0;
            totalGoodsPool = 0;
            totalLifeStylePool = 0;

            for(int r = 0; r < grid.length; r++){
                for(int c = 0; c < grid[0].length; c++) {
                    Cell currentCell = grid[r][c];
                    if (currentCell instanceof Zone){
                        Zone currentZone = (Zone) currentCell;
                        if(currentCell.getSymbol() == 'H'){
                            totalPopulationPool += currentZone.getOutput();
                        }else if(currentCell.getSymbol() == 'I'){
                            totalGoodsPool += currentZone.getOutput();
                        }else if(currentCell.getSymbol() == 'C'){
                            totalLifeStylePool += currentZone.getOutput();
                        }
                    }
                }
            }

            writer.writeResult("output.txt");
        }
    }
}
