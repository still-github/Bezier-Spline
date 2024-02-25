package TheBetterSpline;

import MathStuff.*;

public class SplineTesting {
    public static void main(String[] args) {

        Spline spline = new Spline(new Point(-1.18,8.9), 1.2, new RobotPose(1.2, -4.9, 0));

        System.out.println(spline.fromLine());

    }
}
