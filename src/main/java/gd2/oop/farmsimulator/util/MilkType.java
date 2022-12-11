package gd2.oop.farmsimulator.util;

public enum MilkType {
    EMPTY("Empty"),
    COW("Cows Milk"),
    GOAT("Goats Milk");

    private final String name;

    MilkType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static MilkType fromName(String name) {
        for (MilkType type : MilkType.values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        return null;
    }
}