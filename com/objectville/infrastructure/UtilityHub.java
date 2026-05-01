package com.objectville.infrastructure;

import com.objectville.core.Cell;

public abstract class UtilityHub extends Cell {
    private static final int MAX_CAPACITY = 100;
    private int currentUsage;

    public UtilityHub(int x, int y, char symbol) {
        super(x, y, symbol);
        this.currentUsage = 0;
    }

    public int getAvailableCapacity() {
        return MAX_CAPACITY - currentUsage;
    }

    public void consumeCapacity(int amount) {
        this.currentUsage += amount;
    }

    public void resetUsage() {
        this.currentUsage = 0;
    }

    public int getCurrentUsage() {
        return currentUsage;
    }
}
