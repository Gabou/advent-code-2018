package repose_record;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GuardsTest {

    @Test
    void non_regression() throws IOException {
        Guards.GuardKataResult guardKataResult = Guards.guardKata();
        Assertions.assertThat(guardKataResult.part1).isEqualTo(77084);
        Assertions.assertThat(guardKataResult.part2).isEqualTo(23047);
    }
}