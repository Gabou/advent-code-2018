package chronal_charge;

import java.util.Objects;

public class CellsGroup {
    private final Position position;
    private final Size size;
    private FuelLevel fuelLevel;

    public CellsGroup(Position position, Size size, FuelLevel fuelLevel) {

        this.position = position;
        this.size = size;
        this.fuelLevel = fuelLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellsGroup that = (CellsGroup) o;
        return Objects.equals(position, that.position) &&
                Objects.equals(size, that.size) &&
                Objects.equals(fuelLevel, that.fuelLevel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(position, size, fuelLevel);
    }

    @Override
    public String toString() {
        return "CellsGroup{" +
                "position=" + position +
                ", size=" + size +
                ", fuelLevel=" + fuelLevel +
                '}';
    }

    public boolean hasMoreFuelThan(CellsGroup cellsGroup) {
        if (cellsGroup == null) {
            return true;
        }

        return fuelLevel.isLargerThan(cellsGroup.fuelLevel);
    }

    protected FuelLevel fuelLevel() {
        return fuelLevel;
    }

    protected Position position() {
        return position;
    }
}
