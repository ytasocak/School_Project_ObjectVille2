package com.objectville.model.utility;

import com.objectville.model.interfaces.IConnectable;

// Water provider building (symbol: W)
public class WaterStation extends UtilityHub implements IConnectable {

    public WaterStation(int row, int col) {
        super(row, col, 'W');
    }

    @Override
    public boolean canConduct() {
        return true;
    }
}
