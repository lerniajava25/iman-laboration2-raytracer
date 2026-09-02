import java.util.Optional;

public class Triangle extends Shape {

    private static final double EPSILON = 1e-9;

    private final Vector3D vertexA;
    private final Vector3D vertexB;
    private final Vector3D vertexC;

    public Triangle(
            Vector3D vertexA,
            Vector3D vertexB,
            Vector3D vertexC,
            Color color
    ) {
        super(color);

        if (vertexA == null || vertexB == null || vertexC == null) {
            throw new IllegalArgumentException(
                    "Triangle vertices cannot be null."
            );
        }

        validateFinite(vertexA, "vertexA");
        validateFinite(vertexB, "vertexB");
        validateFinite(vertexC, "vertexC");

        Vector3D edge1 = vertexB.subtract(vertexA);
        Vector3D edge2 = vertexC.subtract(vertexA);

        Vector3D normal = edge1.cross(edge2);

        validateFinite(normal, "triangle normal");

        if (normal.length() <= EPSILON) {
            throw new IllegalArgumentException(
                    "Triangle vertices must not be collinear."
            );
        }

        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.vertexC = vertexC;
    }

    public Vector3D getVertexA() {
        return vertexA;
    }

    public Vector3D getVertexB() {
        return vertexB;
    }

    public Vector3D getVertexC() {
        return vertexC;
    }

    @Override
    public Optional<HitRecord> hit(Ray ray) {
        if (ray == null) {
            throw new IllegalArgumentException(
                    "Ray cannot be null."
            );
        }

        Vector3D edge1 = vertexB.subtract(vertexA);
        Vector3D edge2 = vertexC.subtract(vertexA);

        Vector3D h = ray.getDirection().cross(edge2);
        double determinant = edge1.dot(h);

        if (Math.abs(determinant) < EPSILON) {
            return Optional.empty();
        }

        double inverseDeterminant = 1.0 / determinant;

        Vector3D s = ray.getOrigin().subtract(vertexA);
        double u = inverseDeterminant * s.dot(h);

        if (u < 0.0 || u > 1.0) {
            return Optional.empty();
        }

        Vector3D q = s.cross(edge1);

        double v =
                inverseDeterminant
                        * ray.getDirection().dot(q);

        if (v < 0.0 || u + v > 1.0) {
            return Optional.empty();
        }

        double distance =
                inverseDeterminant * edge2.dot(q);

        if (distance <= 0.0) {
            return Optional.empty();
        }

        Vector3D hitPoint =
                ray.at(distance);

        Vector3D normal =
                edge1.cross(edge2).normalize();

        HitRecord hitRecord =
                new HitRecord(
                        distance,
                        hitPoint,
                        normal,
                        getColor()
                );

        return Optional.of(hitRecord);
    }

    private void validateFinite(
            Vector3D vector,
            String name
    ) {
        if (!Double.isFinite(vector.getX())
                || !Double.isFinite(vector.getY())
                || !Double.isFinite(vector.getZ())) {
            throw new IllegalArgumentException(
                    name + " must contain only finite values."
            );
        }
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertexA=" + vertexA +
                ", vertexB=" + vertexB +
                ", vertexC=" + vertexC +
                ", color=" + getColor() +
                '}';
    }
}