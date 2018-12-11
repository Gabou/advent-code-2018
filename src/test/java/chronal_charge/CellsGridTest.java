package chronal_charge;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CellsGridTest {

    @Test
    void give_cells_goup_fuel_of_size_3_3_at_33_45_with_serial_number_18() {
        CellsGrid cellsGrid = new CellsGrid(new Size(300, 300), 18);
        FuelLevel fuelLevel = cellsGrid.powerOfCellsGroup(new Position(33,45),new Size(3, 3));
        Assertions.assertThat(fuelLevel).isEqualTo(new FuelLevel(29));
    }


    @Test
    void give_cells_group_origin_coordinate_with_largest_fuel_with_serial_number_18() {
        CellsGrid cellsGrid = new CellsGrid(new Size(300, 300), 18);
        Position originPosition = cellsGrid.cellsGroupsOriginCoordinateWithLargestFuel(new Size(3, 3));
        Assertions.assertThat(originPosition).isEqualTo(new Position(33,45));
    }
}
