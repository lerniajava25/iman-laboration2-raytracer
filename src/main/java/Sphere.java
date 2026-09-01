import java.util.Optional;

public class Sphere extends Shape {

    private static final double EPSILON = 1e-9;

    private final Vector3D center;
    private final double radius;

    public Sphere(Vector3D center, double radius, Color color) {
        super(color);

        if (center == null) {
            throw new IllegalArgumentException(
                    "Sphere center cannot be null."
            );
        }

        if (!Double.isFinite(center.getX())
                || !Double.isFinite(center.getY())
                || !Double.isFinite(center.getZ())) {
            throw new IllegalArgumentException(
                    "Sphere center must contain only finite values."
            );
        }

        if (!Double.isFinite(radius) || radius <= 0) {
            throw new IllegalArgumentException(
                    "Sphere radius must be a finite positive value."
            );
        }

        this.center = center;
        this.radius = radius;
    }

    public Vector3D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public Optional<HitRecord> hit(Ray ray) {
        if (ray == null) {
            throw new IllegalArgumentException(
                    "Ray cannot be null."
            );
        }

        Vector3D originToCenter =
                ray.getOrigin().subtract(center);

        Vector3D direction = ray.getDirection();

        double a = direction.dot(direction);
        double halfB = originToCenter.dot(direction);
        double c = originToCenter.dot(originToCenter)
                - radius * radius;

        double discriminant =
                halfB * halfB - a * c;

        if (discriminant < 0) {
            return Optional.empty();
        }

        double squareRoot =
                Math.sqrt(discriminant);

        double root =
                (-halfB - squareRoot) / a;

        if (root <= EPSILON) {
            root = (-halfB + squareRoot) / a;

            if (root <= EPSILON) {
                return Optional.empty();
            }
        }

        Vector3D hitPoint =
                ray.at(root);

        Vector3D outwardNormal =
                hitPoint
                        .subtract(center)
                        .multiply(1.0 / radius);

        HitRecord hitRecord =
                new HitRecord(
                        root,
                        hitPoint,
                        outwardNormal,
                        getColor()
                );

        return Optional.of(hitRecord);
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                ", color=" + getColor() +
                '}';
    }
}