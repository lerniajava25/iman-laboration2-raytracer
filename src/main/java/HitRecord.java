public class HitRecord {

    private final double distance;
    private final Vector3D point;
    private final Vector3D normal;
    private final Color color;

    public HitRecord(
            double distance,
            Vector3D point,
            Vector3D normal,
            Color color
    ) {
        if (!Double.isFinite(distance) || distance < 0) {
            throw new IllegalArgumentException(
                    "Hit distance must be a finite non-negative value."
            );
        }

        if (point == null) {
            throw new IllegalArgumentException(
                    "Hit point cannot be null."
            );
        }

        if (normal == null) {
            throw new IllegalArgumentException(
                    "Hit normal cannot be null."
            );
        }

        if (!Double.isFinite(normal.getX())
                || !Double.isFinite(normal.getY())
                || !Double.isFinite(normal.getZ())) {
            throw new IllegalArgumentException(
                    "Hit normal must contain only finite values."
            );
        }

        if (color == null) {
            throw new IllegalArgumentException(
                    "Hit color cannot be null."
            );
        }

        this.distance = distance;
        this.point = point;
        this.normal = normal.normalize();
        this.color = color;
    }

    public double getDistance() {
        return distance;
    }

    public Vector3D getPoint() {
        return point;
    }

    public Vector3D getNormal() {
        return normal;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "HitRecord{" +
                "distance=" + distance +
                ", point=" + point +
                ", normal=" + normal +
                ", color=" + color +
                '}';
    }
}