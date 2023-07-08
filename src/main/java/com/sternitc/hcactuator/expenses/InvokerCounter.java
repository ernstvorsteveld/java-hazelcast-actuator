package com.sternitc.hcactuator.expenses;

import lombok.Getter;

@Getter
public class InvokerCounter {

    private int count = 0;

    public void add() {
        this.count++;
    }
}
