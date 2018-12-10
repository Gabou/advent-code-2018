package fabric;

import java.util.Objects;

public class ID {

    private int id;

    public ID(int id) {

        this.id = id;
    }

    public ID(String stringID) {
        id = Integer.valueOf(stringID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ID id1 = (ID) o;
        return id == id1.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ID{" +
                "id=" + id +
                '}';
    }
}
