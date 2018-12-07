package fabric;

public class ElfClaim {
    private Position position;
    private Size size;

    public ElfClaim(Position position, Size size) {
        this.position = position;
        this.size = size;
    }

    public boolean onSquare(Position position) {
        return position.in(this.position, size);
    }
}
