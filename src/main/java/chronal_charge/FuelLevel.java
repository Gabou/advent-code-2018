package chronal_charge;

import java.util.Objects;

public class FuelLevel {
    private int level;

    public FuelLevel(int level) {

        this.level = level;
    }

    public void add(FuelLevel fuelLevel) {
        level += fuelLevel.level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelLevel fuelLevel = (FuelLevel) o;
        return level == fuelLevel.level;
    }

    @Override
    public int hashCode() {

        return Objects.hash(level);
    }

    @Override
    public String toString() {
        return "FuelLevel{" +
                "level=" + level +
                '}';
    }

    public boolean isLargerThan(FuelLevel largestFuelLevel) {
        return level > largestFuelLevel.level;
    }
}
