package gd2.oop.farmsimulator.animals.raw;

import java.util.UUID;

public abstract class Cow extends Animal {
    protected Cow() {}

    protected Cow(String name) {
        super(name);
    }

    protected Cow(String name, UUID id) {
        super(name, id);
    }
}