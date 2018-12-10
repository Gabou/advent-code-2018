package fabric;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(String string) {
        String[] coordinates = string.split(",");
        x = Integer.valueOf(coordinates[0]);
        y = Integer.valueOf(coordinates[1]);

    }

    public boolean in(Position position, Size size) {
        return this.x >= position.x && (this.x < position.x + size.height()
                || this.y >= position.y) && this.y < position.y + size.width();


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}
