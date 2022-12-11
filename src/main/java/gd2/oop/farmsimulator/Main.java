package gd2.oop.farmsimulator;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import gd2.oop.farmsimulator.animals.meat.BeefCow;
import gd2.oop.farmsimulator.animals.meat.Sheep;
import gd2.oop.farmsimulator.animals.milk.DairyCow;
import gd2.oop.farmsimulator.animals.milk.Goat;
import gd2.oop.farmsimulator.animals.raw.Animal;
import gd2.oop.farmsimulator.interfaces.IMilkable;
import gd2.oop.farmsimulator.machines.MilkingMachine;
import gd2.oop.farmsimulator.storage.MilkTank;
import gd2.oop.farmsimulator.structures.Farm;
import gd2.oop.farmsimulator.structures.Shed;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();
    public static final Faker faker = new Faker();
    private static final ArrayList<Farm> farms = new ArrayList<>();
    private static final String filePath = "farmdata.json";

    public static void main(String... args) {
        if (!loadFarms()) {
            System.out.println("Failed to load farm data.");
            System.out.println("Create a new farm to override corrupted data.");
            scanner.nextLine();
        } else {
            if (farms.size() == 1) {
                System.out.println("Loaded 1 farm.");
            } else {
                System.out.println("Loaded " + farms.size() + " farms.");
            }
        }
        mainMenu();
    }

    private static boolean loadFarms() {
        try {
            FileReader reader = new FileReader(filePath);
            Gson gson = new Gson();
            JsonObject data = gson.fromJson(reader, JsonObject.class);
            JsonArray array = data.get("farms").getAsJsonArray();
            farms.clear();
            for (int i = 0; i < array.size(); i++) {
                JsonObject farmData = array.get(i).getAsJsonObject();
                Farm farm = Farm.fromJson(farmData);
                farms.add(farm);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No farm data found.");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static void saveFarms() {
        try {
            Gson gson = new Gson();
            JsonArray array = new JsonArray();
            for (Farm farm : farms) {
                JsonElement farmData = farm.toJson();
                array.add(farmData);
            }
            JsonObject data = new JsonObject();
            data.add("farms", array);
            String jsonData = gson.toJson(data);
            FileWriter writer = new FileWriter(filePath);
            writer.write(jsonData);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mainMenu() {
        while (true) {
            System.out.println();
            System.out.println("Farm Simulator");
            System.out.println("1. New Farm");
            System.out.println("2. Select Farm");
            System.out.println("3. Delete Farm");
            System.out.println("4. Exit");
            System.out.print("Input: ");
            int input = safeInputInt(scanner.nextLine());
            switch (input) {
                case 1 -> newFarm();
                case 2 -> selectFarm();
                case 3 -> {
                    for (int i = 0; i < farms.size(); i++) {
                        Farm farm = farms.get(i);
                        System.out.println(i + ". " + farm.getOwner());
                    }
                    System.out.print("Enter remove which farm: ");
                    int index = safeInputInt(scanner.nextLine());
                    if (index >= 0 && index < farms.size()) {
                        farms.remove(index);
                        System.out.println("Farm deleted.");
                    } else {
                        System.out.println("Farm not found.");
                    }
                    continue;
                }
                case 4 -> System.exit(0);
                default -> {
                    System.out.println("Invalid Input.");
                    continue;
                }
            }
            return;
        }
    }

    private static void newFarm() {
        System.out.println();
        System.out.println("New Farm");
        System.out.print("Farm Owner's name: ");
        String name = scanner.nextLine();
        Farm farm = new Farm(name);
        farms.add(farm);
        saveFarms();
        farmMenu(farm);
    }

    private static void selectFarm() {
        while (true) {
            System.out.println();
            System.out.println("Select Farm");
            int i = 0;
            for (; i < farms.size(); i++) {
                System.out.println((i + 1) + ". " + farms.get(i).getOwner() + "'s Farm");
            }
            System.out.println((i + 1) + ". Back");
            System.out.print("Input: ");
            int input = safeInputInt(scanner.nextLine());
            if (input <= farms.size() && input > 0) {
                Farm farm = farms.get(input - 1);
                farmMenu(farm);
            } else if (input == farms.size() + 1) {
                mainMenu();
            } else {
                System.out.println("Invalid Input.");
                continue;
            }
            return;
        }
    }

    private static void farmMenu(Farm farm) {
        while (true) {
            saveFarms();
            System.out.println();
            System.out.println(farm.getOwner() + "'s Farm");
            System.out.println("1. View Sheds");
            System.out.println("2. Add Shed");
            System.out.println("3. View Animals");
            System.out.println("4. Add Animal");
            System.out.println("5. Sort Animals by Value");
            System.out.println("6. View Farm Info");
            System.out.println("7. Milk all Animals");
            System.out.println("8. Collect farm milk");
            System.out.println("9. Back");
            System.out.print("Input: ");
            int input = safeInputInt(scanner.nextLine());
            switch (input) {
                case 1 -> viewSheds(farm);
                case 2 -> addShed(farm);
                case 3 -> animalsMenu(farm);
                case 4 -> addAnimal(farm);
                case 5 -> sortAnimals(farm);
                case 6 -> viewFarmInfo(farm);
                case 7 -> milkAnimals(farm);
                case 8 -> collectFarmMilk(farm);
                case 9 -> mainMenu();
                default -> {
                    System.out.println("Invalid Input.");
                    continue;
                }
            }
            return;
        }
    }

    private static void viewSheds(Farm farm) {
        if (farm.getSheds().size() > 0) {
            System.out.println();
            System.out.println(farm.getOwner() + "'s Sheds");
            for (int i = 0; i < farm.getSheds().size(); i++) {
                System.out.println((i + 1) + ". " + farm.getSheds().get(i).toString());
            }
            System.out.println((farm.getSheds().size() + 1) + ". Back");
            System.out.print("Input: ");
            int input = safeInputInt(scanner.nextLine());
            if ((input <= farm.getSheds().size()) && (input > 0)) {
                shedMenu(farm, farm.getSheds().get(input - 1));
            } else if (input == (farm.getSheds().size() + 1)) {
                farmMenu(farm);
            } else {
                System.out.println("Invalid Input.");
                viewSheds(farm);
            }
        } else {
            System.out.println();
            System.out.println(farm.getOwner() + " has no sheds.");
        }
        farmMenu(farm);
    }

    private static void shedMenu(Farm farm, Shed shed) {
        while (true) {
            System.out.println();
            System.out.println(shed.toString());
            System.out.println("1. Rename Shed");
            System.out.println("2. Add Milking Machine");
            System.out.println("3. Remove Shed");
            System.out.println("4. Back");
            System.out.print("Input: ");
            int input = safeInputInt(scanner.nextLine());
            switch (input) {
                case 1 -> {
                    System.out.print("New name: ");
                    String name = scanner.nextLine();
                    for (Shed s : farm.getSheds()) {
                        if (s.getName().equals(name)) {
                            System.out.println("Shed with that name already exists.");
                            shedMenu(farm, shed);
                        }
                    }
                    shed.setName(name);
                    System.out.println("Shed renamed.");
                    continue;
                }
                case 2 -> {
                    shed.installMilkingMachine(new MilkingMachine());
                    System.out.println("Milking Machine added.");
                    continue;
                }
                case 3 -> {
                    farm.removeShed(shed);
                    System.out.println("Shed removed.");
                    farmMenu(farm);
                }
                case 4 -> viewSheds(farm);
                default -> {
                    System.out.println("Invalid Input.");
                    continue;
                }
            }
            return;
        }
    }

    private static void addShed(Farm farm) {
        System.out.println();
        System.out.println("Add Shed");
        System.out.print("Shed Name: ");
        String name = scanner.nextLine();
        for (Shed shed : farm.getSheds()) {
            if (shed.getName().equals(name)) {
                System.out.println("Shed already exists.");
                addShed(farm);
            }
        }
        System.out.print("Shed Milk Tank Capacity: ");
        int capacity = safeInputInt(scanner.nextLine());
        Shed shed;
        if (capacity > 0) {
            shed = new Shed(new MilkTank(capacity));
        } else {
            shed = new Shed(new MilkTank());
        }
        shed.setName(name);
        farm.addShed(shed);
        farmMenu(farm);
    }

    private static void animalsMenu(Farm farm) {
        if (farm.getHerds().size() > 0) {
            System.out.println();
            System.out.println(farm.getOwner() + "'s Animals");
            Class<? extends Animal> lastClass = null;
            int i = 0;
            for (Entry<Class<? extends Animal>, Animal> animals : farm.getHerds().entries()) {
                if (lastClass != animals.getKey()) {
                    System.out.println(farm.getOwner() + "'s %ss".formatted(animals.getKey().getSimpleName()));
                    lastClass = animals.getKey();
                }
                System.out.println((i + 1) + ". " + animals.getValue().toString());
                i++;
            }
            System.out.println((i + 1) + ". Back");
            System.out.print("Input: ");
            int input = safeInputInt(scanner.nextLine());
            if ((input <= farm.getHerds().size()) && (input > 0)) {
                Animal animal = farm.getHerds().values().toArray(new Animal[0])[input - 1];
                animalMenu(farm, animal);
            } else if (input == (farm.getHerds().size() + 1)) {
                farmMenu(farm);
            } else {
                System.out.println("Invalid Input.");
                animalsMenu(farm);
            }
        } else {
            System.out.println();
            System.out.println(farm.getOwner() + " has no animals.");
        }
        farmMenu(farm);
    }

    private static void animalMenu(Farm farm, Animal animal) {
        System.out.println();
        System.out.println(animal.toString());
        System.out.println("1. Edit Name");
        System.out.println("2. Kill Animal");
        System.out.println("3. Back");
        System.out.print("Input: ");
        int input = safeInputInt(scanner.nextLine());
        switch (input) {
            case 1 -> {
                System.out.println();
                System.out.println("New Name: ");
                String name = scanner.nextLine();
                animal.setName(name);
            }
            case 2 -> farm.removeFromHerd(animal);
            case 3 -> animalsMenu(farm);
            default -> {
                System.out.println("Invalid Input.");
                animalMenu(farm, animal);
            }
        }
        animalsMenu(farm);
    }

    private static void addAnimal(Farm farm) {
        System.out.println();
        System.out.println("Add Animal");
        System.out.println("1. Beef Cow");
        System.out.println("2. Sheep");
        System.out.println("3. Dairy Cow");
        System.out.println("4. Goat");
        System.out.println("5. Back");
        System.out.print("Input: ");
        int input = safeInputInt(scanner.nextLine());
        switch (input) {
            case 1 -> {
                System.out.println();
                System.out.println("Beef Cow");
                System.out.print("Name: ");
                String name = scanner.nextLine();
                for (Animal animal : farm.getHerds().values()) {
                    if (animal.getName().equals(name)) {
                        System.out.println("Name already taken.");
                        addAnimal(farm);
                    }
                }
                if ((name == null) || "".equals(name)) {
                    farm.addToHerd(BeefCow.of());
                } else {
                    farm.addToHerd(BeefCow.of(name));
                }
            }
            case 2 -> {
                System.out.println();
                System.out.println("Sheep");
                System.out.print("Name: ");
                String name = scanner.nextLine();
                for (Animal animal : farm.getHerds().values()) {
                    if (animal.getName().equals(name)) {
                        System.out.println("Name already taken.");
                        addAnimal(farm);
                    }
                }
                if ((name == null) || "".equals(name)) {
                    farm.addToHerd(Sheep.of());
                } else {
                    farm.addToHerd(Sheep.of(name));
                }
            }
            case 3 -> {
                System.out.println();
                System.out.println("Dairy Cow");
                System.out.print("Name: ");
                String name = scanner.nextLine();
                for (Animal animal : farm.getHerds().values()) {
                    if (animal.getName().equals(name)) {
                        System.out.println("Name already taken.");
                        addAnimal(farm);
                    }
                }
                if ((name == null) || "".equals(name)) {
                    farm.addToHerd(DairyCow.of());
                } else {
                    farm.addToHerd(DairyCow.of(name));
                }
            }
            case 4 -> {
                System.out.println();
                System.out.println("Goat");
                System.out.print("Name: ");
                String name = scanner.nextLine();
                for (Animal animal : farm.getHerds().values()) {
                    if (animal.getName().equals(name)) {
                        System.out.println("Name already taken.");
                        addAnimal(farm);
                    }
                }
                if ((name == null) || "".equals(name)) {
                    farm.addToHerd(Goat.of());
                } else {
                    farm.addToHerd(Goat.of(name));
                }
            }
            case 5 -> farmMenu(farm);
            default -> {
                System.out.println("Invalid Input.");
                addAnimal(farm);
            }
        }
        farmMenu(farm);
    }

    private static void sortAnimals(Farm farm) {
        System.out.println();
        if (farm.getHerds().size() > 0) {
            System.out.println(farm.getOwner() + "'s Animals by Value");
            farm.getHerds().entries().stream().sorted().forEach(animal -> System.out.println(animal.getValue() + " - " + animal.getValue().getName()));
        } else {
            System.out.println(farm.getOwner() + " has no animals.");
        }
        farmMenu(farm);
    }

    private static void viewFarmInfo(Farm farm) {
        System.out.println();
        System.out.println("Farm Info");
        System.out.println(farm.toString());
        farmMenu(farm);
    }

    private static void milkAnimals(Farm farm) {
        System.out.println();
        if (farm.getHerds().size() > 0) {
            for (Entry<Class<? extends Animal>, Animal> animals : farm.getHerds().entries()) {
                if (animals.getValue() instanceof IMilkable milkable) {
                    if (farm.getSheds().size() > 0) {
                        boolean machines = false;
                        for (Shed shed : farm.getSheds()) {
                            if (shed.hasMachine()) {
                                machines = true;
                                break;
                            }
                        }
                        if (!machines) {
                            System.out.println("No milking machines available.");
                            farmMenu(farm);
                            return;
                        }
                        for (Shed shed : farm.getSheds()) {
                            if (shed.hasMachine()) {
                                try {
                                    shed.getMachine().milk(milkable);
                                } catch (Exception ignored) {
                                }
                            }
                            if (milkable.getUdder().getFullness() <= 0) {
                                break;
                            }
                        }
                    } else {
                        System.out.println("No sheds.");
                    }
                }
                if (random.nextInt(100) == 0) {
                    Animal animal = animals.getValue();
                    farm.removeFromHerd(animal);
                    System.out.println(animal.getName() + " died.");
                }
            }
        } else {
            System.out.println(farm.getOwner() + " has no animals.");
        }
        farmMenu(farm);
    }

    private static void collectFarmMilk(Farm farm) {
        double taken = 0;
        for (Shed shed : farm.getSheds()) {
            taken += shed.getMilkTank().getFromTank(shed.getMilkTank().getCapacity());
        }
        System.out.println("Collected " + taken + "L of milk from " + farm.getOwner() + "'s Farm.");
        farmMenu(farm);
    }

    public static int safeInputInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}