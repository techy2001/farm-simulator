package gd2.oop.farmsimulator.machines;

import gd2.oop.farmsimulator.animals.milk.DairyCow;
import gd2.oop.farmsimulator.storage.MilkTank;
import gd2.oop.farmsimulator.storage.Udder;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class MilkingMachineTest {
    @Test
    void getMilkTank() {
        MilkingMachine machine = new MilkingMachine();
        assertNull(machine.getMilkTank());
    }

    @Test
    void setMilkTank() {
        MilkingMachine machine = new MilkingMachine();
        MilkTank tank = new MilkTank(100);
        machine.setMilkTank(tank);
        assertEquals(tank, machine.getMilkTank());
    }

    @Test
    void milk() {
        MilkingMachine machine = new MilkingMachine();
        MilkTank tank = new MilkTank(100);
        machine.setMilkTank(tank);
        DairyCow cow = DairyCow.of("Barry", UUID.fromString("00000000-0000-0000-0000-000000000000"), new Udder(30, 30));
        machine.milk(cow);
        assertTrue(tank.freeSpace() < 100);
    }
}