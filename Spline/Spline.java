package Spline;

import java.util.stream.DoubleStream;

public class Spline {

    //TODO: add distance as a factor for desiredT

    
    private double[][] points;
    private double correctionDistance;
    private double[] robotPosition;



    /**
     * this uses cubic bezier so there should be 4 points, the first and last are the start and end points, the middle two are the points that define the curve.
     * Highly recommend graphing the curves using Desmos (link in readme).
     * @param points input points (should be 4)
     * @param correctionDistance how far off should the bot be before curving back on
     * @param robotPosition starting point of robot
     */
    public Spline(double[][] points, double correctionDistance, double[] robotPosition){
        
        this.points = points;
        this.correctionDistance = correctionDistance;
        this.robotPosition = robotPosition;

    }

    /**
     * updates the position of robot
     * @param robotPosition robots current position (x,y)
     */
    public void update(double[] robotPosition){

        this.robotPosition = robotPosition;

    }


    /**
     * x component of the bezier 
     * @param t since we are using parametrics, t is just the percent along the curve the bot is
     * @return x coordinate of the bot at t 
     */
    private double bezierX(double t){
        
        double x1 = points[0][0];
        double x2 = points[1][0]; 
        double x3 = points[2][0];
        double x4 = points[3][0];

        double bx = Math.pow(1-t,3) * x1 + 3 * t * Math.pow(1-t,2) * x2 + 3* Math.pow(t,2) * (1-t) * x3 + Math.pow(t, 3) * x4;

        return bx;

    }
    
    /**
     * y component of the bezier 
     * @param t since we are using parametrics, t is just the percent along the curve the bot is
     * @return y coordinate of the bot at t 
     */
    private double bezierY(double t){
        
        double y1 = points[0][1];
        double y2 = points[1][1]; 
        double y3 = points[2][1];
        double y4 = points[3][1];

        double by = Math.pow(1-t,3) * y1 + 3 * t * Math.pow(1-t,2) * y2 + 3* Math.pow(t,2) * (1-t) * y3 + Math.pow(t, 3) * y4;

        return by;

    }

    /**
     * gives distance between a value (t) on the curve and the robot
     * @param t used for parametrics, value from (0,1)
     * @return gives distance between robot and the input t value
     */
    private double distance(double t){

        double d = Math.sqrt(Math.pow(bezierY(t) - robotPosition[1], 2) + Math.pow(bezierX(t) - robotPosition[0], 2));

        return d;

    }
    // desiredT() is currently public for testing purposes, I'll make it private later
    /**
     * used to find optimal t value for bezier 
     * samples points along the curve to find the
     * @return closest t value to the robot (from 0 to 1)
     */
    public double desiredT(){

        double height = 1000000;

        double desiredT = 0;

        //number of points sampled
        double splineSample = 50;


        double[] intersections = DoubleStream.iterate(0, n -> n + 1 / splineSample).limit((int)splineSample).toArray();

        for (double i : intersections){
            if(distance(i) < height){

                height = distance(i);
                desiredT = i;

            }
            
        }

        return desiredT;
    }

}
