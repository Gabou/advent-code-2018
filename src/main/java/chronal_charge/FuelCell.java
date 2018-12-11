package chronal_charge;

public class FuelCell {
    private final Position position;
    private final FuelLevel fuelLevel;

    public FuelCell(Position position, int serialNumber) {

        this.position = position;

        int rackId = position.x() + 10;
        int powerLevel = (rackId * position.y() + serialNumber) * rackId / 100 % 10 - 5;
        fuelLevel = new FuelLevel(powerLevel);
    }

    public FuelLevel fuelLevel() {
        return fuelLevel;
    }

    @Override
    public String toString() {
        return "FuelCell{" + position + fuelLevel + '}';
    }
}
