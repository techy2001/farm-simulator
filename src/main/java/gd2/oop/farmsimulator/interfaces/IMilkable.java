package gd2.oop.farmsimulator.interfaces;

import gd2.oop.farmsimulator.util.MilkType;
import gd2.oop.farmsimulator.storage.Udder;

public interface IMilkable {
    /**
     * Get the udder of the animal.
     *
     * @return the udder of the animal.
     */
    Udder getUdder();

    /**
     * Get the milk type of the animal.
     *
     * @return the milk type of the animal.
     */
    MilkType getMilkType();

    /**
     * Refills the animal's udder.
     */
    default void refreshUdder() {
        this.getUdder().refresh();
    }

    /**
     * @return Gets the milk capacity from the animal's udder.
     */
    default double getCapacity() {
        return this.getUdder().getCapacity();
    }
}