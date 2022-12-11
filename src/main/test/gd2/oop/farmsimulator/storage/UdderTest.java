package gd2.oop.farmsimulator.storage;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class UdderTest {
    @Test
    void getCapacity() {
        Udder udder = new Udder(100, 100);
        assertEquals(100, udder.getCapacity());
    }

    @Test
    void getFullness() {
        Udder udder = new Udder(100, 100);
        assertEquals(100, udder.getFullness());
    }

    @Test
    void setFullness() {
        Udder udder = new Udder(100, 100);
        udder.setFullness(50);
        assertEquals(50, udder.getFullness());
    }

    @Test
    void refresh() {
        Udder udder = new Udder(100, 100);
        udder.setFullness(50);
        udder.refresh();
        assertEquals(100, udder.getFullness());
    }

    @Test
    void milk() {
        Udder udder = new Udder(100, 100);
        udder.milk();
        assertTrue(udder.getFullness() < 100);
    }

    @Test
    void milk2() {
        Udder udder = new Udder(100, 100);
        udder.milk(20);
        assertTrue(udder.getFullness() >= 80);
    }

    @Test
    void milk3() {
        Udder udder = new Udder(100, 100);
        udder.milk(Optional.of(30d));
        assertTrue(udder.getFullness() >= 70);
    }
}