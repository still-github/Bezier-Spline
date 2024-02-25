package TheBetterSpline;

import MathStuff.*;

public class SplineTesting {
    public static void main(String[] args) {

        Spline spline = new Spline(new Point(-12.7,-11.8), 2, new RobotPose(8.0, 69.0, 0));

        System.out.println(spline.driveVector().x);
        System.out.println(spline.driveVector().y);

    }
}
