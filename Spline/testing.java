package Spline;

public class testing {
    
    public static double[][] points = new double[][]{{17.5,-72},{5.8,32.3},{82.5,-13.4},{54.8,83.7}};
    public static double[] robotStartingPos = new double[]{32.3,-28};
    public static Spline spline = new Spline(points, 20, robotStartingPos);
    public static void main(String args[]){

        System.out.println(spline.angle());
        System.out.println(spline.desiredT());



    }
    
}
