package day12;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import reader.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class GenerationTest {

    @Test
    void give_20th_generation_from_testInputDay12() throws IOException {

        PlantGeneration initialGeneration = PlantGeneration.plantGeneration("/testInputDay12");
        int generation20 = initialGeneration.growTo(2000);
        Assertions.assertThat(generation20).isEqualTo(325);
    }


}
