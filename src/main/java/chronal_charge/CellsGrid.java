package chronal_charge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CellsGrid {

    private final List<FuelCell> grid;
    private final Size size;

    public CellsGrid(Size size, int serialNumber) {
        this.size = size;
        grid = new ArrayList<>();
        for (int x = 0; x < size.width(); x++) {
            for (int y = 0; y < size.height(); y++) {
               grid.add(new FuelCell(new Position(x,y),serialNumber));
            }

        }

    }

    public FuelLevel powerOfCellsGroup(Position position, Size size) {

        FuelLevel fuelLevel = new FuelLevel(0);

        for (FuelCell fuelCell : grid) {
            if(fuelCell.isInGroup(position, size)) {
                fuelLevel.add(fuelCell.fuelLevel());
            }
        }

        return fuelLevel;
    }

    public Position cellsGroupsOriginCoordinateWithLargestFuel(Size size) {
        Map<FuelLevel, Position> fuelLevelByGroup = new HashMap<>();

        for (int x = 0; x < this.size.width() - size.width(); x++) {
            for (int y = 0; y < this.size.height() - size.height(); y++) {
                Position position = new Position(x, y);
                fuelLevelByGroup.put(powerOfCellsGroup(position, size), position);
            }
        }

        FuelLevel largestFuelLevel = new FuelLevel(-1000);

        for (FuelLevel fuelLevel : fuelLevelByGroup.keySet()) {
            if (fuelLevel.isLargerThan(largestFuelLevel)) {
                largestFuelLevel = fuelLevel;
            }
        }

        return fuelLevelByGroup.get(largestFuelLevel);

    }

    private CellsGroup cellsGroupsLargestFuel(Size size) {
        Map<FuelLevel, Position> fuelLevelByGroup = new HashMap<>();

        for (int x = 0; x < this.size.width() - size.width(); x++) {
            for (int y = 0; y < this.size.height() - size.height(); y++) {
                Position position = new Position(x, y);
                fuelLevelByGroup.put(powerOfCellsGroup(position, size), position);
            }
        }

        FuelLevel largestFuelLevel = new FuelLevel(-1000);

        for (FuelLevel fuelLevel : fuelLevelByGroup.keySet()) {
            if (fuelLevel.isLargerThan(largestFuelLevel)) {
                largestFuelLevel = fuelLevel;
            }
        }

        return new CellsGroup(fuelLevelByGroup.get(largestFuelLevel),size,largestFuelLevel);

    }

    public CellsGroup cellsGroupsWithLargestFuel() {
        List<CellsGroup> cellsGroups = new ArrayList<>();
        for (int width = 0; width < size.width(); width++) {
            for (int height = 0; height < size.height(); height++) {
                cellsGroups.add(cellsGroupsLargestFuel(new Size(width, height)));
            }
        }

        CellsGroup cellsGroup = null;

        for (CellsGroup group : cellsGroups) {
            if(cellsGroup == null) {
                cellsGroup = group;
                continue;
            }

            if(group.hasLargerFuelLevel(cellsGroup)) {
                cellsGroup = group;
            }
        }

        return cellsGroup;
    }

    public static void main(String[] args) {
        CellsGrid cellsGrid = new CellsGrid(new Size(300, 300), 9435);
        System.out.println(cellsGrid.cellsGroupsOriginCoordinateWithLargestFuel(new Size(3,3)));
    }
}
