package chronal_charge;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CellsGridTest {

    @Test
    void give_cells_group_fuel_of_size_3_3_at_33_45_with_serial_number_18() {
        CellsGrid cellsGrid = new CellsGrid(new Size(300), 18);
        CellsGroup cellsGroup = cellsGrid.cellsGroup(new Position(33,45),new Size(3));
        Assertions.assertThat(cellsGroup.fuelLevel()).isEqualTo(new FuelLevel(29));
    }


    @Test
    void give_cells_group_origin_coordinate_with_largest_fuel_with_serial_number_18() {
        CellsGrid cellsGrid = new CellsGrid(new Size(300), 18);
        CellsGroup cellsGroup = cellsGrid.cellsGroupsWithLargestFuel(new Size(3));
        Assertions.assertThat(cellsGroup.position()).isEqualTo(new Position(33,45));
    }

    @Test
    void give_cells_group_with_largest_fuel_with_serial_number_18() {
        CellsGrid cellsGrid = new CellsGrid(new Size(300), 18);
        CellsGroup cellsGroup = cellsGrid.cellsGroupsWithLargestFuel();
        Assertions.assertThat(cellsGroup).isEqualTo(new CellsGroup(new Position(90,269), new Size(16), new FuelLevel(113)));
    }

    @Test
    void give_cells_group_with_largest_fuel_with_serial_number_42() {
        CellsGrid cellsGrid = new CellsGrid(new Size(300), 42);
        CellsGroup cellsGroup = cellsGrid.cellsGroupsWithLargestFuel();
        Assertions.assertThat(cellsGroup).isEqualTo(new CellsGroup(new Position(232,251), new Size(12), new FuelLevel(119)));
    }
}
