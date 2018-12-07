package fabric;

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
}
