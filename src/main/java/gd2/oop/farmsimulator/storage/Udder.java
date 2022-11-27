package gd2.oop.farmsimulator.storage;

import gd2.oop.farmsimulator.Main;

import java.util.logging.Level;

public class Udder {
    private static final int STANDARD_CAPACITY = 2000;
    private final double capacity;
    private double fullness;

    public Udder() {
        this(STANDARD_CAPACITY);
    }

    public Udder(int capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return this.capacity;
    }

    @Override
    public String toString() {
        return "MilkTank{" +
                "capacity=" + this.capacity +
                ", fullness=" + this.fullness +
                '}';
    }
}