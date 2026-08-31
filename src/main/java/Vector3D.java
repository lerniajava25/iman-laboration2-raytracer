public class Vector3D {

    private final double x;
    private final double y;
    private final double z;

    public Vector3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vector3D add(Vector3D other) {
        return new Vector3D(
                x + other.x,
                y + other.y,
                z + other.z
        );
    }

    public Vector3D subtract(Vector3D other) {
        return new Vector3D(
                x - other.x,
                y - other.y,
                z - other.z
        );
    }

    public Vector3D multiply(double scalar) {
        return new Vector3D(
                x * scalar,
                y * scalar,
                z * scalar
        );
    }

    public double dot(Vector3D other) {
        return x * other.x
                + y * other.y
                + z * other.z;
    }

    public double length() {
        return Math.sqrt(dot(this));
    }

    public Vector3D normalize() {

        double length = length();

        if (length == 0) {
            throw new IllegalStateException(
                    "En nollvektor kan inte normaliseras."
            );
        }

        return multiply(1.0 / length);
    }

    @Override
    public String toString() {
        return "Vector3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}