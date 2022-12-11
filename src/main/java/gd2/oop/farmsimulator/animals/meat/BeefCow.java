package gd2.oop.farmsimulator.animals.meat;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import gd2.oop.farmsimulator.Main;
import gd2.oop.farmsimulator.animals.raw.Cow;
import gd2.oop.farmsimulator.interfaces.IMeatable;
import gd2.oop.farmsimulator.interfaces.ISaveable;

import java.util.UUID;
import java.util.function.Function;

public class BeefCow extends Cow implements IMeatable, ISaveable<BeefCow> {
    private double weight = Main.random.nextDouble(800, 1200);
    private double pedigreeMultiplier = Main.random.nextDouble(0.5, 1.5);
    private double age = Main.random.nextDouble(0, 10);

    protected BeefCow() {}

    protected BeefCow(String name) {
        super(name);
    }

    protected BeefCow(String name, UUID id) {
        super(name, id);
    }

    /**
     * @return A new instance of the animal.
     */
    public static BeefCow of() {
        return new BeefCow();
    }

    /**
     * @param name The name of the animal.
     * @return A new instance of the animal.
     */
    public static BeefCow of(String name) {
        return new BeefCow(name);
    }

    /**
     * @param name The name of the animal.
     * @param id The name of the animal.
     * @return A new instance of the animal.
     */
    public static BeefCow of(String name, UUID id) {
        return new BeefCow(name, id);
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
        StringBuilder string = new StringBuilder("BeefCow");
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

    public static BeefCow fromJson(JsonElement element) throws JsonParseException {
        JsonObject object = element.getAsJsonObject();
        BeefCow cow = new BeefCow(object.get("name").getAsString(), UUID.fromString(object.get("id").getAsString()));
        cow.age = object.get("age").getAsDouble();
        cow.weight = object.get("weight").getAsDouble();
        cow.pedigreeMultiplier = object.get("pedigreeMultiplier").getAsDouble();
        return cow;
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
    public Function<JsonElement, BeefCow> jsonFunction() {
        return BeefCow::fromJson;
    }
}