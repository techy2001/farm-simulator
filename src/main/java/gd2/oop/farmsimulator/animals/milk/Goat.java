package gd2.oop.farmsimulator.animals.milk;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import gd2.oop.farmsimulator.Main;
import gd2.oop.farmsimulator.animals.raw.Animal;
import gd2.oop.farmsimulator.interfaces.IMilkable;
import gd2.oop.farmsimulator.interfaces.ISaveable;
import gd2.oop.farmsimulator.util.MilkType;
import gd2.oop.farmsimulator.storage.Udder;

import java.util.UUID;
import java.util.function.Function;

public class Goat extends Animal implements IMilkable, ISaveable<Goat> {
    private final Udder udder;

    protected Goat() {
        this(Main.faker.name().firstName());
    }

    protected Goat(String name) {
        super(name);
        this.udder = new Udder(2, 3);
    }

    protected Goat(String name, UUID id, Udder udder) {
        super(name, id);
        this.udder = udder;
    }

    public static Goat of() {
        return new Goat();
    }

    public static Goat of(String name) {
        return new Goat(name);
    }

    public static Goat of(String name, UUID id, Udder udder) {
        return new Goat(name, id, udder);
    }

    @Override
    public Udder getUdder() {
        return this.udder;
    }

    @Override
    public MilkType getMilkType() {
        return MilkType.GOAT;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Goat");
        this.addString(string);
        return string.toString();
    }

    @Override
    public void addString(StringBuilder builder) {
        super.addString(builder);
        builder.append("\n\tudder = ").append(this.udder);
    }

    @Override
    protected int getValue() {
        return (int) (this.udder.getCapacity() * 10);
    }

    public static Goat fromJson(JsonElement element) throws JsonParseException {
        JsonObject object = element.getAsJsonObject();
        Udder udder = Udder.fromJson(object.get("udder"));
        return new Goat(object.get("name").getAsString(), UUID.fromString(object.get("id").getAsString()), udder);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.add("udder", this.udder.toJson());
        object.addProperty("name", this.getName());
        object.addProperty("id", this.getId().toString());
        return object;
    }

    @Override
    public Function<JsonElement, Goat> jsonFunction() {
        return Goat::fromJson;
    }
}