package fabric;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ElfClaimTest {

    @Test
    void should_tell_it_on_a_square_at_2_6() {
        ElfClaim elfClaim = new ElfClaim(new ID(1), new Position(1,3), new Size(4,4));
        Assertions.assertThat(elfClaim.onSquare(new Position(2,6))).isTrue();
    }

    @Test
    void should_tell_if_it_not_on_a_square_at_0_1() {
        ElfClaim elfClaim = new ElfClaim(new ID(1), new Position(1,3), new Size(4,4));
        Assertions.assertThat(elfClaim.onSquare(new Position(0,1))).isFalse();
    }

    @Test
    void should_give_all_position_used() {
        ElfClaim elfClaim = new ElfClaim(new ID(1), new Position(1,3), new Size(2,1));
        Assertions.assertThat(elfClaim.zone()).isEqualTo(Arrays.asList(new Position(1,3), new Position(2,3)));
    }
}
