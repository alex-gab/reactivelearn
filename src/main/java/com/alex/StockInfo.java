package com.alex;

import java.util.Random;

public final class StockInfo {
    private final String info;
    private final double value;

    private StockInfo(String info, double value) {
        this.info = info;
        this.value = value;
    }

    public static StockInfo fetch(String stockSymbol) {
        return new StockInfo("info for " + stockSymbol, new Random().nextDouble() * 100);
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "StockInfo{" +
                "info='" + info + '\'' +
                ", value=" + value +
                '}';
    }
}
