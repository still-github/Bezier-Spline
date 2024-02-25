package TheBetterSpline;

import MathStuff.*;

public class SplineTesting {
    public static void main(String[] args) {

        Spline spline = new Spline(new Point(55.4, 36.2), 0.8, new RobotPose(0, 0, 0));

        spline.update(new RobotPose(-47.0, 14.6, 0));

        System.out.println(spline.driveVector().x);
        System.out.println(spline.driveVector().y);

    }
}
