package gd2.oop.farmsimulator.structures;

import gd2.oop.farmsimulator.animals.raw.Animal;

import java.util.ArrayList;
import java.util.HashMap;

public class Farm {
    private static int nextFarm = 0;
    private int farmID;
    private Owner owner;
    private Shed shed;
    private HashMap<Animal, ArrayList<Animal>> herds = new HashMap();

    public Farm() {
        this.farmID = nextFarm;
        nextFarm++;
    }
}