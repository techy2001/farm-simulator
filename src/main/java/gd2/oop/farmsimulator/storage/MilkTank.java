package gd2.oop.farmsimulator.storage;

import gd2.oop.farmsimulator.Main;

import java.util.logging.Level;

public class MilkTank {
    private static final int STANDARD_CAPACITY = 2000;
    private final double capacity;
    private double fullness;
//    private MilkType milkType = MilkType.EMPTY;

    public MilkTank() {
        this(STANDARD_CAPACITY);
    }

    public MilkTank(int capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return this.capacity;
    }

    public double freeSpace() {
        return this.capacity - this.fullness;
    }

    public void addToTank(double amount) {
        this.fullness += amount;
        if (this.fullness > this.capacity) {
            this.fullness = this.capacity;
        }
    }

    public double getFromTank(double amount) {
        double amountTaken = Math.min(this.fullness, amount);
        this.fullness -= amountTaken;
        return amountTaken;
    }

    @Override
    public String toString() {
        return "MilkTank{" +
                "capacity=" + this.capacity +
                ", fullness=" + this.fullness +
                '}';
    }
}