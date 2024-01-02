package Spline;

public class testing {
    
    public static double[][] points = new double[][]{{158.5,200},{520,84},{5,68},{172.6,29.3}};
    public static double[] robotStartingPos = new double[]{185,204};
    public static Spline spline = new Spline(points, 20, robotStartingPos);
    public static void main(String args[]){

        System.out.println(spline.angle());
        System.out.println(spline.desiredT());



    }
    
}
