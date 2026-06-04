package com.objectville.model.zone;

public class Commercial extends Zone {

    public Commercial(int x,int y,char symbol){
        super(x,y,symbol);
    }

    @Override
    public void updateZone(){
        int m = Math.min(electricityReceived,waterReceived);
        m = Math.min(m,internetReceived);

        if (m== 0){
            level=0;
            output=0;
            utilityDemand=1;
            return;
        }

        int targetLevel = 0;

        if (m > 0) {
            targetLevel = 1;
        }

        if (m > 0 && hasSecurity) {
            targetLevel = 2;
        }

        if (hasSecurity && populationReceived > m && goodsReceived > m) {
            targetLevel = 3;
        }

        if (level < targetLevel) {
            level++;
        } else if (level > targetLevel) {
            level--;
        }

        output = ProductivityEngine.calculateCommercialOutput(level, m, populationReceived, goodsReceived);
        utilityDemand = Math.max(output, 1);
    }

}
