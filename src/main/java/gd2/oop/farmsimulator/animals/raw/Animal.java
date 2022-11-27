package gd2.oop.farmsimulator.animals.raw;

public abstract class Animal {
    private final String name;

    protected Animal() {
        this("RandomName");
    }

    protected Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + this.name + '\'' +
                '}';
    }
}