package gd2.oop.farmsimulator.structures;

import gd2.oop.farmsimulator.animals.meat.Sheep;
import gd2.oop.farmsimulator.storage.MilkTank;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class FarmTest {
    @Test
    void setOwner() {
        Farm farm = new Farm("Johnathan Doe");
        farm.setOwner("Jane Doe");
        assertEquals("Jane Doe", farm.getOwner());
    }

    @Test
    void getHerds() {
        Farm farm = new Farm("Jane Doe");
        assertEquals(0, farm.getHerds().size());
    }

    @Test
    void getAnimal() {
        Farm farm = new Farm("Jane Doe");
        Sheep barry = Sheep.of("Barry", UUID.fromString("00000000-0000-0000-0000-000000000000"));
        farm.addToHerd(barry);
        assertEquals(barry, farm.getAnimal(UUID.fromString("00000000-0000-0000-0000-000000000000")));
    }

    @Test
    void addToHerd() {
        Farm farm = new Farm("Jane Doe");
        Sheep barry = Sheep.of("Barry", UUID.fromString("00000000-0000-0000-0000-000000000000"));
        farm.addToHerd(barry);
        assertEquals(1, farm.getHerds().size());
        assertTrue(farm.getHerds().containsValue(barry));
    }

    @Test
    void removeFromHerd() {
        Farm farm = new Farm("Jane Doe");
        Sheep barry = Sheep.of("Barry", UUID.fromString("00000000-0000-0000-0000-000000000000"));
        farm.addToHerd(barry);
        assertEquals(1, farm.getHerds().size());
        assertTrue(farm.getHerds().containsValue(barry));
        farm.removeFromHerd(barry);
        assertEquals(0, farm.getHerds().size());
        assertFalse(farm.getHerds().containsValue(barry));
    }

    @Test
    void addShed() {
        Farm farm = new Farm("Jane Doe");
        Shed shed = new Shed(new MilkTank(100));
        assertEquals(0, farm.getSheds().size());
        assertFalse(farm.getSheds().contains(shed));
        farm.addShed(shed);
        assertEquals(1, farm.getSheds().size());
        assertTrue(farm.getSheds().contains(shed));
    }

    @Test
    void removeShed() {
        Farm farm = new Farm("Jane Doe");
        Shed shed = new Shed(new MilkTank(100));
        assertEquals(0, farm.getSheds().size());
        assertFalse(farm.getSheds().contains(shed));
        farm.addShed(shed);
        assertEquals(1, farm.getSheds().size());
        assertTrue(farm.getSheds().contains(shed));
        farm.removeShed(shed);
        assertEquals(0, farm.getSheds().size());
        assertFalse(farm.getSheds().contains(shed));
    }
}