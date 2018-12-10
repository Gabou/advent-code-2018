package fabric;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ElfClaim {
    private ID id;
    private Position position;
    private Size size;

    public ElfClaim(ID id, Position position, Size size) {
        this.id = id;
        this.position = position;
        this.size = size;
    }

    public boolean onSquare(Position position) {
        return position.in(this.position, size);
    }

    public ID id() {
        return id;
    }

    public Position position() {
        return position;
    }

    public Size size() {
        return size;
    }

    public List<Position> zone() {
        List<Position> positions = new ArrayList<>();

        for (int x = position.x(); x < position.x() + size.width(); x++) {
            for (int y = position.y(); y < position.y() + size.height(); y++) {
                positions.add(new Position(x,y));
            }
        }



        return positions;
    }

    public Integer squaresNumber() {
        return size.height() * size.width();
    }
}
