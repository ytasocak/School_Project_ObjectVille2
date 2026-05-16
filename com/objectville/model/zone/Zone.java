package com.objectville.model.zone;

import com.objectville.model.base.Cell;
import com.objectville.model.interfaces.IPowerable;
import com.objectville.model.interfaces.IWaterable;

public abstract class Zone extends Cell implements IPowerable, IWaterable {
    protected int level;
    protected int electricityReceived;
    protected int waterReceived;
    protected int internetReceived;
    protected int populationReceived;
    protected int goodsReceived;
    protected int lifestyleReceived;
    protected boolean hasSecurity;
    protected boolean hasHealth;
    protected boolean hasEducation;
    protected int output;
    protected int utilityDemand;

    public Zone(int x, int y, char symbol) {
        super(x, y, symbol);
        this.level = 0;
        this.utilityDemand = 1;
    }

    public abstract void updateZone();

    public int getLevel() {
        return level;
    }

    public int getOutput() {
        return output;
    }

    public int getUtilityDemand() {
        return utilityDemand;
    }

    public void setSecurity(boolean hasSecurity) {
        this.hasSecurity = hasSecurity;
    }

    public void setHealth(boolean hasHealth) {
        this.hasHealth = hasHealth;
    }

    public void setEducation(boolean hasEducation) {
        this.hasEducation = hasEducation;
    }

    public void receiveElectricity(int amount) {
        electricityReceived += amount;
    }

    public void receiveWater(int amount) {
        waterReceived += amount;
    }

    public void receiveInternet(int amount) {
        internetReceived += amount;
    }

    public void receivePopulation(int amount) {
        populationReceived += amount;
    }

    public void receiveGoods(int amount) {
        goodsReceived += amount;
    }

    public void receiveLifestyle(int amount) {
        lifestyleReceived += amount;
    }

    public void resetTickData() {
        electricityReceived = 0;
        waterReceived = 0;
        internetReceived = 0;
        populationReceived = 0;
        goodsReceived = 0;
        lifestyleReceived = 0;
        hasEducation = false;
        hasHealth = false;
        hasSecurity = false;
    }

}
