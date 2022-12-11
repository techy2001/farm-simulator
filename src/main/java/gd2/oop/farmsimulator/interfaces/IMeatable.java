package gd2.oop.farmsimulator.interfaces;

public interface IMeatable {
    /**
     * @param weight The weight of the animal.
     */
    void setWeight(double weight);

    /**
     * @param pedigreeMultiplier The value multiplier from the animal's pedigree.
     */
    void setPedigreeMultiplier(double pedigreeMultiplier);

    /**
     * @param age The age of the animal.
     */
    void setAge(double age);

    /**
     * @return The weight of the animal.
     */
    double getWeight();

    /**
     * @return The value multiplier from the animal's pedigree.
     */
    double getPedigreeMultiplier();

    /**
     * @return The age of the animal.
     */
    double getAge();
}