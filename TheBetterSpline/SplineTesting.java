package TheBetterSpline;

import MathStuff.*;

public class SplineTesting {
    public static void main(String[] args) {

        Spline spline = new Spline(new Point(0,0), 0, new RobotPose(14.2, -10.1, 0));

        System.out.println(spline.driveVector().x);
        System.out.println(spline.driveVector().y);

    }
}
