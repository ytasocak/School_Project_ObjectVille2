package com.objectville.model;

public class Industrial extends Zone {

    public Industrial(int x, int y, char symbol){
        super(x,y,symbol);
    }

    @Override
    public void updateZone(){

        int m = Math.min(electricityReceived, waterReceived);

        if(m == 0 || populationReceived == 0){
            level=0;
            output=0;
            utilityDemand=1;
            return;
        }

        int targetLevel = 1;

        if(hasSecurity){
            targetLevel = 2;
        }

        if(hasSecurity && populationReceived> m){
            targetLevel = 3;
        }

        if(level<targetLevel){
            level++;
        } else if (level>targetLevel) {
            level--;
        }

        if(level == 1){
            output=m;
        } else if (level==2){
            output=2*m;
        } else if (level==3) {
            output = 2*m+populationReceived;
        }else {
            output=0;
        }

        utilityDemand=Math.max(output,1);

    }
}
