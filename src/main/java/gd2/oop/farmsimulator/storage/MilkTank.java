package gd2.oop.farmsimulator.storage;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import gd2.oop.farmsimulator.interfaces.ISaveable;
import gd2.oop.farmsimulator.util.MilkType;

import java.util.function.Function;

public class MilkTank implements ISaveable<MilkTank> {
    private static final double STANDARD_CAPACITY = 2000;
    private final double capacity;
    private double fullness;
    private MilkType milkType = MilkType.EMPTY;

    /**
     * Constructor for objects of class MilkTank
     */
    public MilkTank() {
        this(STANDARD_CAPACITY);
    }

    /**
     * Constructor which can specify a custom milk tank size.
     */
    public MilkTank(double capacity) {
        this.capacity = capacity;
    }

    /**
     * Constructor which includes all variables.
     */
    public MilkTank(double capacity, double fullness, MilkType milkType) {
        this.capacity = capacity;
        this.fullness = fullness;
        this.milkType = milkType;
    }

    /**
     * @return Returns the maximum capacity of the tank.
     */
    public double getCapacity() {
        return this.capacity;
    }

    /**
     * @return The amount of empty space in the tank.
     */
    public double freeSpace() {
        return this.capacity - this.fullness;
    }

    /**
     * @return Returns if the milk tank is full or not.
     */
    public boolean isFull() {
        return this.fullness >= this.capacity;
    }

    /**
     * @return Returns the amount of milk not inserted.
     */
    public double addToTank(MilkType milkType, double amount) {
        if (this.milkType == MilkType.EMPTY) {
            this.milkType = milkType;
        } else if (this.milkType != milkType) {
            return amount;
        }
        this.fullness += amount;
        if (this.fullness > this.capacity) {
            double overflow = this.fullness - this.capacity;
            this.fullness = this.capacity;
            return overflow;
        }
        return 0;
    }

    /**
     * @return Returns the amount of milk taken from the tank.
     */
    public double getFromTank(double amount) {
        double amountTaken = Math.min(this.fullness, amount);
        this.fullness -= amountTaken;
        if (this.fullness <= 0) {
            this.milkType = MilkType.EMPTY;
        }
        return amountTaken;
    }

    public static MilkTank fromJson(JsonElement element) throws JsonParseException {
        JsonObject object = element.getAsJsonObject();
        return new MilkTank(object.get("capacity").getAsDouble(), object.get("fullness").getAsDouble(), MilkType.fromName(object.get("milkType").getAsString()));
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("capacity", this.capacity);
        object.addProperty("fullness", this.fullness);
        object.addProperty("milkType", this.milkType.getName());
        return object;
    }

    @Override
    public Function<JsonElement, MilkTank> jsonFunction() {
        return MilkTank::fromJson;
    }

    @Override
    public String toString() {
        if (this.milkType == MilkType.EMPTY) {
            return "Milk Tank: " + this.milkType.getName();
        }
        return "Milk Tank: " + this.fullness + '/' + this.capacity + ' ' + this.milkType.getName();
    }
}