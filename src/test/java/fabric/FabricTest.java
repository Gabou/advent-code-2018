package fabric;

import fabric.Fabric;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class FabricTest {

    private List<String> dataSet = Arrays.asList("#1 @ 1,3: 4x4", "#2 @ 3,1: 4x4", "#3 @ 5,5: 2x2");

    @Test
    void should_give_4_conflict_squares_with_dataSet() {
        Assertions.assertThat(Fabric.conflictSquaresCount(dataSet)).isEqualTo(4);
    }




}
