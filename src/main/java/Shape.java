public abstract class Shape implements Hittable {

    private final Color color;

    protected Shape(Color color) {
        if (color == null) {
            throw new IllegalArgumentException(
                    "Shape color cannot be null."
            );
        }

        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "{color=" + color + '}';
    }
}