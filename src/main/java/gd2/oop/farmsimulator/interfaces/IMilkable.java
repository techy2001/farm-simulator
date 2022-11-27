package gd2.oop.farmsimulator.interfaces;

import gd2.oop.farmsimulator.storage.Udder;

import java.util.List;

public interface IMilkable {
    default double getCapacity() {
        return this.getUdders().stream().mapToDouble(Udder::getCapacity).sum();
    }

    List<Udder> getUdders();
}