package com.objectville.model.zone;

public class ProductivityEngine {

    public static int calculateMinimum(int a, int b) {
        return Math.min(a, b);
    }

    public static int calculateMinimum(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public static int calculateHousingOutput(int level, int m, int lifeStyleReceived) {

        if (level == 0) {
            return 0;
        } else if (level == 1) {
            return m;
        } else if (level == 2) {
            return 2 * m;
        } else if (level == 3) {
            return 2 * m + lifeStyleReceived;
        }

        return 0;
    }

    public static int calculateCommercialOutput(int level, int m, int populationReceived, int goodsReceived) {
        if (level == 0) {
            return 0;
        } else if (level == 1) {
            return m;
        } else if (level == 2) {
            return 2 * m;
        } else if (level == 3) {
            return 2 * m + Math.min(populationReceived, goodsReceived);
        }

        return 0;
    }

    public static int calculateIndustrialOutput(int level, int m, int populationReceived){

        if (level == 0){
            return 0;
        }else if (level == 1){
            return m;
        }else if (level ==2){
            return 2*m;
        }else if (level == 3){
            return 2*m+populationReceived;
        }

        return 0;
    }
}

