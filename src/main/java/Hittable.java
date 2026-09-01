import java.util.Optional;

public interface Hittable {

    Optional<HitRecord> hit(Ray ray);
}