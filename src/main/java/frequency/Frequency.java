import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.hash;

public class Frequency {
    private int value;

    public Frequency(int value) {
        this.value = value;
    }

    public void add(Frequency... frequencies) {
        for (Frequency frequency : frequencies) {
            value += frequency.value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Frequency frequency = (Frequency) o;
        return value == frequency.value;
    }

    @Override
    public int hashCode() {
        return hash(value);
    }

    @Override
    public String toString() {
        return "Frequency : " + value;
    }

    public void remove(Frequency... frequencies) {
        for (Frequency frequency : frequencies) {
            value -= frequency.value;
        }
    }

}
