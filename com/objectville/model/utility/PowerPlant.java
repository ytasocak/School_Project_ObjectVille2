package com.objectville.model.utility;

import com.objectville.model.interfaces.IConnectable;

// Electricity provider building (symbol: P)
public class PowerPlant extends UtilityHub implements IConnectable {

    public PowerPlant(int row, int col) {
        super(row, col, 'P');
    }

    @Override
    public boolean canConduct() {
        return true;
    }
}
