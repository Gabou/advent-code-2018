package day12;

public class PotNumber implements Comparable<PotNumber>{
    private final int value;

    public PotNumber(int value) {
        this.value = value;
    }

    public int compareTo(PotNumber otherPotNumber) {
        return value - otherPotNumber.value;
    }

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return "potNumber=" + value;
    }
}
