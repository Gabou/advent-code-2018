package chronal_charge;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FuelCellTest {

    @Test
    void give_fuel_level_of_cell_at_3_5_and_with_serial_number_8() {
        FuelCell fuelCell = new FuelCell(new Position(3,5), 8);
        Assertions.assertThat(fuelCell.fuelLevel()).isEqualTo(new FuelLevel(4));
    }

    @Test
    void give_fuel_level_of_cell_at_33_45_and_with_serial_number_18() {
        FuelCell fuelCell = new FuelCell(new Position(33,45), 18);
        Assertions.assertThat(fuelCell.fuelLevel()).isEqualTo(new FuelLevel(4));
    }

    @Test
    void give_fuel_level_of_cell_at_33_46_and_with_serial_number_18() {
        FuelCell fuelCell = new FuelCell(new Position(33,46), 18);
        Assertions.assertThat(fuelCell.fuelLevel()).isEqualTo(new FuelLevel(3));
    }
}
