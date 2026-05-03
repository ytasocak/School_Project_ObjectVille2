package com.objectville.model;

public class Housing extends Zone {

    @Override
    public void updateZone(){
        int m = Math.min(electricityReceived,waterReceived);
        m = Math.min(m,internetReceived);
        if (m==0){
            level=0;
            output=0;
            utilityDemand=1;
            return;
        }

        int targetLevel = 1;

        if(hasSecurity&&hasEducation&&hasHealth){
            targetLevel = 2;
        }

        if(hasSecurity&&hasEducation&&hasHealth&&lifestyleReceived>0){
            targetLevel=3;

        }

        if(level<targetLevel){
            level++;
        } else if (level>targetLevel) {
            level--;
        }

        if (level == 1) {
            output=m;
        } else if (level==2) {
            output = 2*m;
        } else if (level==3) {
            output = 2 * m + lifestyleReceived;
        }else {
            output=0;
        }

        utilityDemand = Math.max(output,1);

    }

    }