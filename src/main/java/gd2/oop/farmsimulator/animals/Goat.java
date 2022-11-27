package gd2.oop.farmsimulator.animals;

import gd2.oop.farmsimulator.animals.raw.Animal;
import gd2.oop.farmsimulator.interfaces.IMilkable;
import gd2.oop.farmsimulator.storage.Udder;

import java.util.List;

public class Goat extends Animal implements IMilkable {
    private List<Udder> udders;

    @Override
    public List<Udder> getUdders() {
        return this.udders;
    }
}