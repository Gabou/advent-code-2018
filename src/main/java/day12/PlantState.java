package day12;

import java.util.Objects;

public class PlantState {
    private final boolean plantState;

    public PlantState(char plantState) {
        this.plantState = plantState == '#';
    }

    @Override
    public String toString() {
        return "plantState=" + plantState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlantState that = (PlantState) o;
        return plantState == that.plantState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(plantState);
    }
}
