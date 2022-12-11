package gd2.oop.farmsimulator.storage;

import gd2.oop.farmsimulator.util.MilkType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

class MilkTankTest {
    @Test
    void getCapacity() {
        MilkTank tank = new MilkTank(100);
        assertEquals(100, tank.getCapacity());
    }

    @Test
    void isFull() {
        MilkTank tank = new MilkTank(100);
        assertFalse(tank.isFull());
    }

    @Test
    void addToTank() {
        MilkTank tank = new MilkTank(1000);
        tank.addToTank(MilkType.GOAT, 100);
        tank.addToTank(MilkType.COW, 100);
        assertEquals(tank.freeSpace(), 900);
    }

    @Test
    void getFromTank() {
        MilkTank tank = new MilkTank(1000);
        tank.addToTank(MilkType.GOAT, 100);
        tank.addToTank(MilkType.COW, 100);
        tank.getFromTank(50);
        assertEquals(tank.freeSpace(), 950);
    }
}