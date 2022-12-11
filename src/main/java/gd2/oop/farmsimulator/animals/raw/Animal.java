package gd2.oop.farmsimulator.animals.raw;

import gd2.oop.farmsimulator.Main;

import java.util.UUID;

public abstract class Animal implements Comparable<Animal> {
    private final UUID id;
    private String name;

    /**
     * Creates an animal with a random UUID and a random name.
     */
    protected Animal() {
        this(Main.faker.name().firstName());
    }

    /**
     * Creates an animal with the given name and random UUID.
     * @param name Name to give the animal name.
     */
    protected Animal(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    /**
     * Creates an animal with the given name and given UUID.
     * @param name Name to give the animal.
     * @param uuid UUID to give the animal.
     */
    protected Animal(String name, UUID uuid) {
        this.name = name;
        this.id = uuid;
    }

    /**
     * @return The animal's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name The name to give to the animal.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The UUID of the animal.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * @return String representation of this animal.
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Animal");
        this.addString(string);
        return string.toString();
    }

    /**
     * Adds this animal's details to the given string builder.
     * @param builder The string builder to add to.
     */
    public void addString(StringBuilder builder) {
        builder.append("\n\tname = '").append(this.name).append('\'');
        builder.append("\n\tid = ").append(this.id);
    }

    /**
     * @return The value of the animal.
     */
    protected abstract int getValue();

    @Override
    public int compareTo(Animal o) {
        return this.getValue() - o.getValue();
    }
}