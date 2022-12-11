package gd2.oop.farmsimulator.structures;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import gd2.oop.farmsimulator.animals.raw.Animal;
import gd2.oop.farmsimulator.interfaces.IMilkable;
import gd2.oop.farmsimulator.interfaces.ISaveable;
import gd2.oop.farmsimulator.machines.MilkingMachine;
import gd2.oop.farmsimulator.storage.MilkTank;

import java.util.Collection;
import java.util.UUID;
import java.util.function.Function;

public class Shed implements ISaveable<Shed> {
    private final UUID id;
    private final MilkTank tank;
    private String name;
    private MilkingMachine machine;

    public Shed(MilkTank tank) {
        this(tank, false, UUID.randomUUID());
    }

    public Shed(MilkTank tank, boolean hasMachine, UUID id) {
        this.tank = tank;
        if (hasMachine) {
            this.machine = new MilkingMachine();
        }
        this.id = id;
    }

    /**
     * @return Returns the Milk Tank installed in the shed or null if none is installed.
     */
    public MilkTank getMilkTank() {
        return this.tank;
    }

    /**
     * @param machine The Milking Machine to install in the shed.
     */
    public void installMilkingMachine(MilkingMachine machine) {
        this.machine = machine;
        this.machine.setMilkTank(this.tank);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasMachine() {
        return this.machine != null;
    }

    public MilkingMachine getMachine() {
        return this.machine;
    }

    /**
     * Milks the given animals.
     * @param animal The Animal to milk.
     */
    public void milkAnimal(Animal animal) {
        if (this.machine == null) {
            throw new IllegalStateException("Milking machine is not installed");
        }
        if (animal instanceof IMilkable milkable) {
            this.machine.milk(milkable);
        } else {
            throw new IllegalArgumentException("Animal is not milkable");
        }
    }

    /**
     * Milks all animals in the given collection.
     * @param animals The animals to milk.
     */
    public void milkAnimal(Collection<Animal> animals) {
        for (Animal animal : animals) {
            this.milkAnimal(animal);
        }
    }

    @Override
    public String toString() {
        return "Shed: " + this.name +
                "\n\tid = " + this.id +
                "\n\ttank = " + this.tank +
                "\n\tmachine = " + this.machine;
    }

    public static Shed fromJson(JsonElement element) throws JsonParseException {
        JsonObject object = element.getAsJsonObject();
        MilkTank tank = MilkTank.fromJson(object.get("tank"));
        return new Shed(tank, object.get("hasMachine").getAsBoolean(), UUID.fromString(object.get("id").getAsString()));
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("id", this.id.toString());
        object.addProperty("hasMachine", this.machine != null);
        object.add("tank", this.tank.toJson());
        return object;
    }

    @Override
    public Function<JsonElement, Shed> jsonFunction() {
        return Shed::fromJson;
    }
}