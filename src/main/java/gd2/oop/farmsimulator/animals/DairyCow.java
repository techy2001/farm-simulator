package gd2.oop.farmsimulator.animals;

import gd2.oop.farmsimulator.animals.raw.Cow;
import gd2.oop.farmsimulator.interfaces.IMilkable;
import gd2.oop.farmsimulator.storage.Udder;

import java.util.List;

public class DairyCow extends Cow implements IMilkable {
    private List<Udder> udders;

    @Override
    public List<Udder> getUdders() {
        return this.udders;
    }
}