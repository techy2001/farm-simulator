package gd2.oop.farmsimulator.interfaces;

import com.google.gson.JsonElement;

import java.util.function.Function;

public interface ISaveable<T extends Object> {
    /**
     * @return The JSON representation of the object.
     */
    JsonElement toJson();

    Function<JsonElement, T> jsonFunction();
}