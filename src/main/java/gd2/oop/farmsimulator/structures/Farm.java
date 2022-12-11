package gd2.oop.farmsimulator.structures;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import gd2.oop.farmsimulator.animals.meat.BeefCow;
import gd2.oop.farmsimulator.animals.meat.Sheep;
import gd2.oop.farmsimulator.animals.milk.DairyCow;
import gd2.oop.farmsimulator.animals.milk.Goat;
import gd2.oop.farmsimulator.animals.raw.Animal;
import gd2.oop.farmsimulator.interfaces.ISaveable;

import java.util.ArrayList;
import java.util.UUID;
import java.util.function.Function;

public class Farm implements ISaveable<Farm> {
    private final UUID farmID;
    private String owner;
    private final ArrayList<Shed> sheds = new ArrayList<>();
    private final Multimap<Class<? extends Animal>, Animal> herds = HashMultimap.create();

    public Farm(String ownerName) {
        this.farmID = UUID.randomUUID();
        this.owner = ownerName;
    }

    public Farm(UUID farmID, String ownerName) {
        this.farmID = farmID;
        this.owner = ownerName;
    }

    /**
     * @return Returns the name of the owner of the farm.
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * @param owner The new farm owner's name.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Multimap<Class<? extends Animal>, Animal> getHerds() {
        return this.herds;
    }

    /**
     * Gets an animal from the farm.
     * @param animalID The ID of the animal to get.
     * @return The animal with the given ID.
     */
    public Animal getAnimal(UUID animalID) {
        for (Animal animal : this.herds.values()) {
            if (animal.getId().equals(animalID)) {
                return animal;
            }
        }
        return null;
    }

    /**
     * Adds an animal to the farm.
     * @param animal The animal to add.
     */
    public void addToHerd(Animal animal) {
        this.herds.put(animal.getClass(), animal);
    }

    /**
     * Removes an animal from the farm.
     * @param animal The animal to remove.
     */
    public void removeFromHerd(Animal animal) {
        this.herds.remove(animal.getClass(), animal);
    }

    /**
     * Adds a shed to the farm.
     */
    public void addShed(Shed shed) {
        this.sheds.add(shed);
    }


    public void removeShed(Shed shed) {
        this.sheds.remove(shed);
    }

    public static Farm fromJson(JsonElement element) throws JsonParseException {
        JsonObject object = element.getAsJsonObject();
        Farm farm = new Farm(UUID.fromString(object.get("farmID").getAsString()), object.get("owner").getAsString());
        for (JsonElement shedElement : object.get("shed").getAsJsonArray()) {
            farm.addShed(Shed.fromJson(shedElement));
        }
        for (JsonElement herdElement : object.get("herds").getAsJsonArray()) {
            JsonObject herdObject = herdElement.getAsJsonObject();
            String animalClass = herdObject.get("animalClass").getAsString();
            Function<JsonElement, Animal> animalFactory = getAnimalFunction(animalClass);
            for (JsonElement animalElement : herdObject.get("herd").getAsJsonArray()) {
                farm.addToHerd(animalFactory.apply(animalElement));
            }
        }
        return farm;
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("farmID", this.farmID.toString());
        object.addProperty("owner", this.owner);
        JsonArray shedArray = new JsonArray();
        for (Shed shed : this.sheds) {
            shedArray.add(shed.toJson());
        }
        object.add("shed", shedArray);
        JsonArray herdsArray = new JsonArray();
        for (Class<? extends Animal> animalClass : this.herds.keySet()) {
            JsonObject herdObject = new JsonObject();
            herdObject.addProperty("animalClass", animalClass.getName());
            JsonArray herdArray = new JsonArray();
            for (Animal animal : this.herds.get(animalClass)) {
                if (animal instanceof ISaveable<?> saveable) {
                    herdArray.add(saveable.toJson());
                }
            }
            herdObject.add("herd", herdArray);
            herdsArray.add(herdObject);
        }
        object.add("herds", herdsArray);
        return object;
    }

    @Override
    public Function<JsonElement, Farm> jsonFunction() {
        return Farm::fromJson;
    }

    private static Class<? extends Animal> getAnimalClass(String className) {
        return switch (className) {
            case "gd2.oop.farmsimulator.animals.milk.BeefCow" -> BeefCow.class;
            case "gd2.oop.farmsimulator.animals.milk.Sheep" -> Sheep.class;
            case "gd2.oop.farmsimulator.animals.milk.DairyCow" -> DairyCow.class;
            case "gd2.oop.farmsimulator.animals.milk.Goat" -> Goat.class;
            default -> throw new IllegalArgumentException("Unknown animal class: " + className);
        };
    }

    private static Function<JsonElement, Animal> getAnimalFunction(String className) {
        return switch (className) {
            case "gd2.oop.farmsimulator.animals.milk.BeefCow" -> BeefCow::fromJson;
            case "gd2.oop.farmsimulator.animals.milk.Sheep" -> Sheep::fromJson;
            case "gd2.oop.farmsimulator.animals.milk.DairyCow" -> DairyCow::fromJson;
            case "gd2.oop.farmsimulator.animals.milk.Goat" -> Goat::fromJson;
            default -> throw new IllegalArgumentException("Unknown animal class: " + className);
        };
    }

    public ArrayList<Shed> getSheds() {
        return this.sheds;
    }

    @Override
    public String toString() {
        return "Farm owned by " + this.owner + " with " + this.herds.size() + " herds and " + this.sheds.size() + " sheds.";
    }
}