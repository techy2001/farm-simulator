package gd2.oop.farmsimulator.machines;

import gd2.oop.farmsimulator.interfaces.IMilkable;
import gd2.oop.farmsimulator.storage.MilkTank;

public class MilkingMachine {
    private MilkTank tank;

    public MilkingMachine() {}

    /**
     * @return Returns the Milk Tank installed in the machine or null if none is installed.
     */
    public MilkTank getMilkTank() {
        return this.tank;
    }

    /**
     * @param tank The Milk Tank to install in the machine.
     */
    public void setMilkTank(MilkTank tank) {
        this.tank = tank;
    }

    /**
     * Milks the given IMilkable.
     * @param animal The Animal to milk.
     */
    public void milk(IMilkable animal) {
        if (this.tank == null) {
            throw new IllegalStateException("No milk tank");
        }
        if (this.tank.freeSpace() <= 0) {
            return;
        }
        this.tank.addToTank(animal.getMilkType(), animal.getUdder().milk(this.tank.freeSpace()));
    }
}