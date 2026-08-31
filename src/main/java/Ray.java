public class Ray {

    private final Vector3D origin;
    private final Vector3D direction;

    public Ray(Vector3D origin, Vector3D direction) {
        if (origin == null) {
            throw new IllegalArgumentException(
                    "Ray origin cannot be null."
            );
        }

        if (direction == null) {
            throw new IllegalArgumentException(
                    "Ray direction cannot be null."
            );
        }

        this.origin = origin;
        this.direction = direction.normalize();
    }

    public Vector3D getOrigin() {
        return origin;
    }

    public Vector3D getDirection() {
        return direction;
    }

    public Vector3D at(double distance) {
        return origin.add(
                direction.multiply(distance)
        );
    }

    @Override
    public String toString() {
        return "Ray{" +
                "origin=" + origin +
                ", direction=" + direction +
                '}';
    }
}