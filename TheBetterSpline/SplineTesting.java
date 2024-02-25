package TheBetterSpline;

import MathStuff.*;

public class SplineTesting {
    public static void main(String[] args) {

        Spline spline = new Spline(new Point(52.4, 34.2), Math.PI / 2, new RobotPose(0, 0, 0));

        spline.update(new RobotPose(-86.0, 88.4, 0));

        System.out.println(spline.driveVector().x);
        System.out.println(spline.driveVector().y);

    }
}
