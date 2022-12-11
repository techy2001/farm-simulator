package gd2.oop.farmsimulator.animals.meat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class SheepTest {
    @Test
    void of2() {
        Sheep sheep = Sheep.of("Barry");
        assertEquals("Barry", sheep.getName());
    }

    @Test
    void of3() {
        Sheep sheep = Sheep.of("Barry", UUID.fromString("00000000-0000-0000-0000-000000000000"));
        assertEquals("Barry", sheep.getName());
        assertEquals(UUID.fromString("00000000-0000-0000-0000-000000000000"), sheep.getId());
    }

    @Test
    void setWeight() {
        Sheep sheep = Sheep.of();
        sheep.setWeight(1000);
        assertEquals(1000, sheep.getWeight());
    }

    @Test
    void setPedigreeMultiplier() {
        Sheep sheep = Sheep.of();
        sheep.setPedigreeMultiplier(1.5);
        assertEquals(1.5, sheep.getPedigreeMultiplier());
    }

    @Test
    void setAge() {
        Sheep sheep = Sheep.of();
        sheep.setAge(10);
        assertEquals(10, sheep.getAge());
    }

    @Test
    void getValue() {
        Sheep sheep = Sheep.of();
        sheep.setWeight(100);
        sheep.setPedigreeMultiplier(1.2);
        sheep.setAge(10);
        assertEquals(20, sheep.getValue());
    }
}