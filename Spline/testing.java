package Spline;

public class testing {
    
    public static double[][] points = new double[][]{{194,178},{-4,183},{216,-40},{-66,58}};
    public static double[] robotStartingPos = new double[]{67.5,52.6};
    public static Spline spline = new Spline(points, 50, robotStartingPos, 100);
    public static void main(String args[]){

        System.out.println(spline.angle());
        System.out.println(spline.desiredT());



    }
    
}
