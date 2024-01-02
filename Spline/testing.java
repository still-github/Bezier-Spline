package Spline;

public class testing {
    
    public static double[][] points = new double[][]{{22,24},{57.5,-13.6},{-35.6,-59.4},{-45,-29}};
    public static double[] robotStartingPos = new double[]{12.4,-10.4};
    public static Spline spline = new Spline(points, 20, robotStartingPos);
    public static void main(String args[]){

        System.out.println(spline.angle());
        System.out.println(spline.desiredT());



    }
    
}
