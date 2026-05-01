package com.objectville.infrastructure;

import com.objectville.core.Cell;
import com.objectville.core.IConnectable;

public class Road extends Cell implements IConnectable {
    public Road(int x, int y) {
        super(x, y, 'R');
    }

    @Override
    public boolean canConduct() {
        return true;
    }
}
