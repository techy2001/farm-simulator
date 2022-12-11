package gd2.oop.farmsimulator.animals.milk;

import gd2.oop.farmsimulator.storage.Udder;
import gd2.oop.farmsimulator.util.MilkType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class DairyCowTest {
    @Test
    void of2() {
        DairyCow cow = DairyCow.of("Belle");
        assertEquals("Belle", cow.getName());
    }

    @Test
    void of3() {
        DairyCow cow = DairyCow.of("Belle", UUID.fromString("00000000-0000-0000-0000-000000000000"), new Udder(3, 3));
        assertEquals("Belle", cow.getName());
        assertEquals(UUID.fromString("00000000-0000-0000-0000-000000000000"), cow.getId());
        assertEquals(3, cow.getUdder().getCapacity());
    }

    @Test
    void getMilkType() {
        DairyCow cow = DairyCow.of();
        assertEquals(MilkType.COW, cow.getMilkType());
    }

    @Test
    void getValue() {
        DairyCow cow = DairyCow.of("Belle", UUID.fromString("00000000-0000-0000-0000-000000000000"), new Udder(30, 30));
        assertEquals(300, cow.getValue());
    }
}