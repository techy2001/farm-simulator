package gd2.oop.farmsimulator.animals.meat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class BeefCowTest {
    @Test
    void of2() {
        BeefCow beefCow = BeefCow.of("Sadie");
        assertEquals("Sadie", beefCow.getName());
    }

    @Test
    void of3() {
        BeefCow beefCow = BeefCow.of("Sadie", UUID.fromString("00000000-0000-0000-0000-000000000000"));
        assertEquals("Sadie", beefCow.getName());
        assertEquals(UUID.fromString("00000000-0000-0000-0000-000000000000"), beefCow.getId());
    }

    @Test
    void setWeight() {
        BeefCow cow = BeefCow.of();
        cow.setWeight(1000);
        assertEquals(1000, cow.getWeight());
    }

    @Test
    void setPedigreeMultiplier() {
        BeefCow cow = BeefCow.of();
        cow.setPedigreeMultiplier(1.5);
        assertEquals(1.5, cow.getPedigreeMultiplier());
    }

    @Test
    void setAge() {
        BeefCow cow = BeefCow.of();
        cow.setAge(10);
        assertEquals(10, cow.getAge());
    }

    @Test
    void getValue() {
        BeefCow cow = BeefCow.of();
        cow.setWeight(1000);
        cow.setPedigreeMultiplier(1.5);
        cow.setAge(10);
        assertEquals(1400, cow.getValue());
    }
}