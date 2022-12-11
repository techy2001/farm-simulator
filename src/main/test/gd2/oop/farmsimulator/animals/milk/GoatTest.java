package gd2.oop.farmsimulator.animals.milk;

import gd2.oop.farmsimulator.storage.Udder;
import gd2.oop.farmsimulator.util.MilkType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class GoatTest {
    @Test
    void of2() {
        Goat goat = Goat.of("Barry");
        assertEquals("Barry", goat.getName());
    }

    @Test
    void of3() {
        Goat goat = Goat.of("Barry", UUID.fromString("00000000-0000-0000-0000-000000000000"), new Udder(3, 3));
        assertEquals("Barry", goat.getName());
        assertEquals(UUID.fromString("00000000-0000-0000-0000-000000000000"), goat.getId());
        assertEquals(3, goat.getUdder().getCapacity());
    }

    @Test
    void getMilkType() {
        Goat goat = Goat.of();
        assertEquals(MilkType.GOAT, goat.getMilkType());
    }

    @Test
    void getValue() {
        Goat goat = Goat.of("Barry", UUID.fromString("00000000-0000-0000-0000-000000000000"), new Udder(30, 30));
        assertEquals(300, goat.getValue());
    }
}