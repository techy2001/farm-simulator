package gd2.oop.farmsimulator.animals.milk;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import gd2.oop.farmsimulator.Main;
import gd2.oop.farmsimulator.animals.raw.Cow;
import gd2.oop.farmsimulator.interfaces.IMilkable;
import gd2.oop.farmsimulator.interfaces.ISaveable;
import gd2.oop.farmsimulator.util.MilkType;
import gd2.oop.farmsimulator.storage.Udder;

import java.util.UUID;
import java.util.function.Function;

public class DairyCow extends Cow implements IMilkable, ISaveable<DairyCow> {
    private final Udder udder;

    protected DairyCow() {
        this(Main.faker.name().firstName());
    }

    protected DairyCow(String name) {
        super(name);
        this.udder = new Udder(2, 3);
    }

    protected DairyCow(String name, UUID id, Udder udder) {
        super(name, id);
        this.udder = udder;
    }

    public static DairyCow of() {
        return new DairyCow();
    }

    public static DairyCow of(String name) {
        return new DairyCow(name);
    }

    public static DairyCow of(String name, UUID id, Udder udder) {
        return new DairyCow(name, id, udder);
    }

    @Override
    public Udder getUdder() {
        return this.udder;
    }

    @Override
    public MilkType getMilkType() {
        return MilkType.COW;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("DairyCow");
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

    public static DairyCow fromJson(JsonElement element) throws JsonParseException {
        JsonObject object = element.getAsJsonObject();
        Udder udder = Udder.fromJson(object.get("udder"));
        return new DairyCow(object.get("name").getAsString(), UUID.fromString(object.get("id").getAsString()), udder);
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
    public Function<JsonElement, DairyCow> jsonFunction() {
        return DairyCow::fromJson;
    }
}