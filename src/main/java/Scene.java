import java.util.ArrayList;
import java.util.List;

public class Scene {

    private final List<Hittable> objects;

    public Scene() {
        objects = new ArrayList<>();
    }

    public void add(Hittable object) {

        if (object == null) {
            throw new IllegalArgumentException(
                    "Object cannot be null."
            );
        }

        objects.add(object);
    }

    public boolean remove(Hittable object) {

        if (object == null) {
            return false;
        }

        return objects.remove(object);
    }

    public List<Hittable> getObjects() {
        return List.copyOf(objects);
    }

    public int size() {
        return objects.size();
    }

    public boolean isEmpty() {
        return objects.isEmpty();
    }

    public void clear() {
        objects.clear();
    }

    @Override
    public String toString() {
        return "Scene{" +
                "objects=" + objects +
                '}';
    }
}