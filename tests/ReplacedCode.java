public class TaskJava {
    private static final double PI = 3.14159265358979323846;
    private final double cx, cy;
    private double r;

    private TaskJava(double r, double x, double y) {
        this.cx = x; // Initialize the instance field cx
        this.cy = y; // Initialize the instance field cy
        this.r = Math.sqrt(cx * cx + cy * cy);
    }

    private double getCenterX() {
        return cx;
    }

    private double getCenterY() {
        return cx;
    }

    private void sayAboutMe() {
        System.out.println("PlaneCircle");
    }

}
