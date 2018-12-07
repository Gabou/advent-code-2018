package frequency;

import frequency.Frequency;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class FrequencyShould {

    @Test
    void add_1_to_a_frequency_of_0() {
        Frequency frequency = new Frequency(0);
        frequency.add(new Frequency(1));
        assertThat(frequency).isEqualTo(new Frequency(1));
    }

    @Test
    void remove_1_to_a_frequency_of_0() {
        Frequency frequency = new Frequency(0);
        frequency.remove(new Frequency(1));
        assertThat(frequency).isEqualTo(new Frequency(-1));
    }

    @Test
    void add_7_and_remove_10_to_a_frequency_of_1() {
        Frequency frequency = new Frequency(1);
        frequency.add(new Frequency(7));
        frequency.remove(new Frequency(10));
        assertThat(frequency).isEqualTo(new Frequency(-2));
    }
}
