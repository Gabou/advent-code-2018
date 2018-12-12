package day12;

import java.util.Objects;

public class Plant implements Comparable<Plant>{
    private final PotNumber potNumber;
    private final PlantState plantState;

    public Plant(PotNumber potNumber, PlantState plantState) {

        this.potNumber = potNumber;
        this.plantState = plantState;
    }

    @Override
    public int compareTo(Plant otherPlant) {
        return otherPlant.potNumber.compareTo(otherPlant.potNumber);
    }

    public PotNumber potNumber() {
        return potNumber;
    }

    public PlantState plantState() {
        return plantState;
    }

    @Override
    public String toString() {
        return "Plant{" + potNumber + ", " + plantState + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return Objects.equals(potNumber, plant.potNumber) &&
                Objects.equals(plantState, plant.plantState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(potNumber, plantState);
    }
}
