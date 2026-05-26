package com.objectville.model.utility;

import com.objectville.model.interfaces.IConnectable;

// Internet provider building (symbol: T)
public class InternetHub extends UtilityHub implements IConnectable {

    public InternetHub(int row, int col) {
        super(row, col, 'T');
    }

    @Override
    public boolean canConduct() {
        return true;
    }
}
