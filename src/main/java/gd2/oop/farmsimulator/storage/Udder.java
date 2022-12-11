package gd2.oop.farmsimulator.storage;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import gd2.oop.farmsimulator.Main;
import gd2.oop.farmsimulator.interfaces.ISaveable;

import java.util.Optional;
import java.util.function.Function;

public class Udder implements ISaveable<Udder> {
    private final double capacity;
    private double fullness;

    public Udder(double minCapacity, double maxCapacity) {
        this((Main.random.nextDouble() * (maxCapacity - minCapacity)) + minCapacity);
    }

    private Udder(double capacity) {
        this.capacity = capacity;
        this.fullness = capacity;
    }

    /**
     * Get the capacity of the udder.
     * @return The capacity of the udder.
     */
    public double getCapacity() {
        return this.capacity;
    }

    /**
     * Get the fullness of the udder.
     * @return The fullness of the udder.
     */
    public double getFullness() {
        return this.fullness;
    }

    /**
     * Set the fullness of the udder.
     * @param fullness The fullness of the udder.
     */
    public void setFullness(double fullness) {
        this.fullness = fullness;
    }

    @Override
    public String toString() {
        return "Udder" +
                "\n\tcapacity = " + this.capacity +
                "\n\tfullness = " + this.fullness;
    }

    /**
     * Refills the udder.
     */
    public void refresh() {
        this.fullness = this.capacity;
    }

    /**
     * Get the milk from the udder.
     * @return The milk from the udder.
     */
    public double milk() {
        return this.milk(Optional.empty());
    }

    /**
     * Get the milk from the udder.
     * @param maxTaken The maximum amount of milk to get.
     * @return The milk from the udder.
     */
    public double milk(double maxTaken) {
        return this.milk(Optional.of(maxTaken));
    }

    /**
     * Get the milk from the udder.
     * @param maxTaken The maximum amount of milk to get.
     * @return The milk from the udder.
     */
    public double milk(Optional<Double> maxTaken) {
        double amount = Main.random.nextFloat() * this.fullness;
        if (maxTaken.isPresent() && (amount > maxTaken.get())) {
            amount = maxTaken.get();
        }
        this.fullness -= amount;
        return amount;
    }

    public static Udder fromJson(JsonElement element) throws JsonParseException {
        JsonObject object = element.getAsJsonObject();
        Udder udder = new Udder(object.get("capacity").getAsDouble());
        udder.fullness = object.get("fullness").getAsDouble();
        return udder;
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("capacity", this.capacity);
        object.addProperty("fullness", this.fullness);
        return object;
    }

    @Override
    public Function<JsonElement, Udder> jsonFunction() {
        return Udder::fromJson;
    }
}