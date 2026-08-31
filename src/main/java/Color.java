public class Color {

    private final double red;
    private final double green;
    private final double blue;

    public Color(double red, double green, double blue) {
        this.red = clamp(red);
        this.green = clamp(green);
        this.blue = clamp(blue);
    }

    public double getRed() {
        return red;
    }

    public double getGreen() {
        return green;
    }

    public double getBlue() {
        return blue;
    }

    public Color multiply(double factor) {
        return new Color(
                red * factor,
                green * factor,
                blue * factor
        );
    }

    public Color add(Color other) {
        return new Color(
                red + other.red,
                green + other.green,
                blue + other.blue
        );
    }

    public int getRed255() {
        return (int) Math.round(red * 255);
    }

    public int getGreen255() {
        return (int) Math.round(green * 255);
    }

    public int getBlue255() {
        return (int) Math.round(blue * 255);
    }

    private double clamp(double value) {
        return Math.max(0.0, Math.min(1.0, value));
    }

    @Override
    public String toString() {
        return "Color{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                '}';
    }
}