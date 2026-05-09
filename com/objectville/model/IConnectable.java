package com.objectville.model;

public interface IConnectable {
    // Returns true if utility (electricity/water/internet) can flow through this cell
    boolean canConduct();
}
