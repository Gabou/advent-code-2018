package fabric;

public class Size {
    private final int width;
    private final int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Size(String stringSize) {
        String[] dimensions = stringSize.split("x");
        width = Integer.valueOf(dimensions[0]);
        height = Integer.valueOf(dimensions[1]);
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }
}
