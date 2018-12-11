package chronal_charge;

public class CellsGrid {

    private final FuelCell[][] grid;
    private final Size size;

    public CellsGrid(Size size, int serialNumber) {
        this.size = size;
        grid = new FuelCell[300][300];
        for (int x = 0; x < size.value(); x++) {
            for (int y = 0; y < size.value(); y++) {
               grid[x][y] = new FuelCell(new Position(x,y), serialNumber);
            }
        }
    }

    public CellsGroup cellsGroup(Position position, Size size) {

        FuelLevel fuelLevel = new FuelLevel(0);

        for (int x = position.x(); x < position.x() + size.value() ; x++) {
            for (int y = position.y(); y < position.y() + size.value(); y++) {
                fuelLevel.add(grid[x][y].fuelLevel());
            }
        }

        return new CellsGroup(position, size, fuelLevel);
    }

    public CellsGroup cellsGroupsWithLargestFuel(Size size) {
        CellsGroup cellsGroupWithLargestFuel = null;
        
        for (int x = 0; x < this.size.value() - size.value(); x++) {
            for (int y = 0; y < this.size.value() - size.value(); y++) {
                Position position = new Position(x, y);
                CellsGroup cellsGroup = cellsGroup(position, size);
                if (cellsGroup.hasMoreFuelThan(cellsGroupWithLargestFuel)) {
                    cellsGroupWithLargestFuel = cellsGroup;
                }
            }
        }

        return cellsGroupWithLargestFuel;

    }

    public CellsGroup cellsGroupsWithLargestFuel() {
        CellsGroup cellsGroupWithLargestFuel = null;
        for (int width = 0; width < size.value(); width++) {
            CellsGroup cellsGroup = cellsGroupsWithLargestFuel(new Size(width));
            if(cellsGroup.hasMoreFuelThan(cellsGroupWithLargestFuel)) {
                cellsGroupWithLargestFuel =cellsGroup;
            } else {
                return cellsGroupWithLargestFuel;
            }
        }

        return cellsGroupWithLargestFuel;
    }

    public static void main(String[] args) {
        CellsGrid cellsGrid = new CellsGrid(new Size(300), 9435);
        System.out.println(cellsGrid.cellsGroupsWithLargestFuel(new Size(3)));
        System.out.println(cellsGrid.cellsGroupsWithLargestFuel());
    }
}
