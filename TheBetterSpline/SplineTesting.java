package TheBetterSpline;

import MathStuff.*;

public class SplineTesting {
    public static void main(String[] args) {

        Spline spline = new Spline(new Point(-41,28.9), Math.PI / 4, new RobotPose(-9.1, -4.7, 0));

        System.out.println(spline.desiredT());


    }
}
