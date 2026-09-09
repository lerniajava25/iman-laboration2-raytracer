import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

public class Main {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 225;

    public static void main(String[] args) throws IOException {

        System.out.println("Startar RaytracerOOP...");

        Scene scene = createScene();

        BufferedImage image = new BufferedImage(
                WIDTH,
                HEIGHT,
                BufferedImage.TYPE_INT_RGB
        );

        render(scene, image);

        File outputFile = new File("render.png");

        boolean imageWritten =
                ImageIO.write(
                        image,
                        "png",
                        outputFile
                );

        if (!imageWritten) {
            throw new IOException(
                    "Kunde inte spara bilden eftersom ingen PNG-writer hittades."
            );
        }

        System.out.println("Rendering klar!");
        System.out.println("Bilden har sparats här:");
        System.out.println(outputFile.getAbsolutePath());
    }

    private static Scene createScene() {

        Scene scene = new Scene();

        // Röd sfär
        scene.add(
                new Sphere(
                        new Vector3D(0.0, 0.0, -1.0),
                        0.5,
                        new Color(0.9, 0.2, 0.2)
                )
        );

        // Blå sfär
        scene.add(
                new Sphere(
                        new Vector3D(0.8, 0.0, -1.8),
                        0.4,
                        new Color(0.2, 0.4, 0.9)
                )
        );

        // Grön triangel
        scene.add(
                new Triangle(
                        new Vector3D(-1.2, -0.4, -2.0),
                        new Vector3D(-0.2, -0.4, -2.0),
                        new Vector3D(-0.7, 0.5, -2.0),
                        new Color(0.2, 0.8, 0.3)
                )
        );

        return scene;
    }

    private static void render(
            Scene scene,
            BufferedImage image
    ) {

        Vector3D cameraOrigin =
                new Vector3D(0.0, 0.0, 0.0);

        double aspectRatio =
                (double) WIDTH / HEIGHT;

        double viewportHeight = 2.0;
        double viewportWidth =
                aspectRatio * viewportHeight;

        double focalLength = 1.0;

        Vector3D horizontal =
                new Vector3D(
                        viewportWidth,
                        0.0,
                        0.0
                );

        Vector3D vertical =
                new Vector3D(
                        0.0,
                        viewportHeight,
                        0.0
                );

        Vector3D lowerLeftCorner =
                cameraOrigin
                        .subtract(
                                horizontal.multiply(0.5)
                        )
                        .subtract(
                                vertical.multiply(0.5)
                        )
                        .subtract(
                                new Vector3D(
                                        0.0,
                                        0.0,
                                        focalLength
                                )
                        );

        for (int y = 0; y < HEIGHT; y++) {

            for (int x = 0; x < WIDTH; x++) {

                double u =
                        (double) x
                                / (WIDTH - 1);

                double v =
                        (double) y
                                / (HEIGHT - 1);

                Vector3D direction =
                        lowerLeftCorner
                                .add(
                                        horizontal.multiply(u)
                                )
                                .add(
                                        vertical.multiply(v)
                                )
                                .subtract(cameraOrigin)
                                .normalize();

                Ray ray =
                        new Ray(
                                cameraOrigin,
                                direction
                        );

                int rgb =
                        traceRay(
                                ray,
                                scene
                        );

                image.setRGB(
                        x,
                        HEIGHT - 1 - y,
                        rgb
                );
            }
        }
    }

    private static int traceRay(
            Ray ray,
            Scene scene
    ) {

        double closestDistance =
                Double.POSITIVE_INFINITY;

        Color closestColor = null;

        for (Hittable object :
                scene.getObjects()) {

            Optional<HitRecord> hit =
                    object.hit(ray);

            if (hit.isPresent()) {

                HitRecord record =
                        hit.get();

                if (record.getDistance()
                        < closestDistance) {

                    closestDistance =
                            record.getDistance();

                    closestColor =
                            record.getColor();
                }
            }
        }

        if (closestColor != null) {
            return colorToRgb(closestColor);
        }

        return skyColor(ray);
    }

    private static int colorToRgb(
            Color color
    ) {

        int red =
                toChannel(color.getRed());

        int green =
                toChannel(color.getGreen());

        int blue =
                toChannel(color.getBlue());

        return (red << 16)
                | (green << 8)
                | blue;
    }

    private static int skyColor(
            Ray ray
    ) {

        double y =
                ray.getDirection()
                        .normalize()
                        .getY();

        double t =
                0.5 * (y + 1.0);

        double red =
                (1.0 - t)
                        + t * 0.5;

        double green =
                (1.0 - t)
                        + t * 0.7;

        double blue = 1.0;

        return (toChannel(red) << 16)
                | (toChannel(green) << 8)
                | toChannel(blue);
    }

    private static int toChannel(
            double value
    ) {

        double clamped =
                Math.max(
                        0.0,
                        Math.min(
                                1.0,
                                value
                        )
                );

        return (int) Math.round(
                clamped * 255.0
        );
    }
}