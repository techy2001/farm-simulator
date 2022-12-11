package gd2.oop.farmsimulator.animals.meat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import gd2.oop.farmsimulator.Main;
import gd2.oop.farmsimulator.animals.raw.Animal;
import gd2.oop.farmsimulator.interfaces.IMeatable;
import gd2.oop.farmsimulator.interfaces.ISaveable;

import java.util.UUID;
import java.util.function.Function;

public class Sheep extends Animal implements IMeatable, ISaveable<Sheep> {
    private double weight = Main.random.nextDouble(70, 100);
    private double pedigreeMultiplier = Main.random.nextDouble(0.5, 1.5);
    private double age = Main.random.nextDouble(0, 7);

    protected Sheep() {}

    protected Sheep(String name) {
        super(name);
    }

    protected Sheep(String name, UUID id) {
        super(name, id);
    }

    /**
     * @return A new instance of the animal.
     */
    public static Sheep of() {
        return new Sheep();
    }

    /**
     * @param name The name of the animal.
     * @return A new instance of the animal.
     */
    public static Sheep of(String name) {
        return new Sheep(name);
    }

    /**
     * @param name The name of the animal.
     * @param id The name of the animal.
     * @return A new instance of the animal.
     */
    public static Sheep of(String name, UUID id) {
        return new Sheep(name, id);
    }

    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void setPedigreeMultiplier(double pedigreeMultiplier) {
        this.pedigreeMultiplier = pedigreeMultiplier;
    }

    @Override
    public void setAge(double age) {
        this.age = age;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public double getPedigreeMultiplier() {
        return this.pedigreeMultiplier;
    }

    @Override
    public double getAge() {
        return this.age;
    }

    @Override
    public int getValue() {
        return (int) ((this.weight * this.pedigreeMultiplier) - (this.age * 10));
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Sheep");
        this.addString(string);
        return string.toString();
    }

    @Override
    public void addString(StringBuilder builder) {
        super.addString(builder);
        builder.append("\n\tweight = ").append(this.weight);
        builder.append("\n\tpedigreeMultiplier = ").append(this.pedigreeMultiplier);
        builder.append("\n\tage = ").append(this.age);
    }

    public static Sheep fromJson(JsonElement element) throws JsonParseException {
        JsonObject object = element.getAsJsonObject();
        Sheep sheep = new Sheep(object.get("name").getAsString(), UUID.fromString(object.get("id").getAsString()));
        sheep.age = object.get("age").getAsDouble();
        sheep.weight = object.get("weight").getAsDouble();
        sheep.pedigreeMultiplier = object.get("pedigreeMultiplier").getAsDouble();
        return sheep;
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("name", this.getName());
        object.addProperty("id", this.getId().toString());
        object.addProperty("age", this.age);
        object.addProperty("weight", this.weight);
        object.addProperty("pedigreeMultiplier", this.pedigreeMultiplier);
        return object;
    }

    @Override
    public Function<JsonElement, Sheep> jsonFunction() {
        return Sheep::fromJson;
    }
}