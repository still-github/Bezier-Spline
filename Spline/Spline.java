package Spline;

import java.util.stream.DoubleStream;

public class Spline{
    /**
     * Main spline class
     */
    private double[] entrance, entranceControl, exit, exitControl;
    private double aligningConstant = 0.005;
    private double correctionP;

    private double robot[];

    /**
     * Sets up spline 
     * 
     * @param entrance starting point for spline
     * @param entranceControl alters curve around entrance
     * @param exit ending point for spline
     * @param exitControl alters curve around exit
     * @param correctionP changes strength of correction
     */
    public Spline(double[] entrance, double[] entranceControl, double[] exit, double[] exitControl, double correctionP){
        
        this.entrance = entrance;
        this.entranceControl = entranceControl;
        this.exit = exit;
        this.exitControl = exitControl;
        this.correctionP = correctionP;

    }

    /**
     * sets up x section of bezier
     * @param t used for paramentrics, from (0,1)
     * @return gives x coordinate for bezier at t 
     */
    private double bezierX(double t){
        
        double x1 = entrance[0];
        double x2 = entranceControl[0]; 
        double x3 = exit[0];
        double x4 = exitControl[0];

        double bx = Math.pow(1-t,3) * x1 + 3 * t * Math.pow(1-t,2) * x2 + 3* Math.pow(t,2) * (1-t) * x3 + Math.pow(t, 3) * x4;

        return bx;

    }
    
    /**
     * sets up y section of bezier
     * @param t used for paramentrics, from (0,1)
     * @return gives y coordinate for bezier at t 
     */
    private double bezierY(double t){
        
        double y1 = entrance[1];
        double y2 = entranceControl[1]; 
        double y3 = exit[1];
        double y4 = exitControl[1];

        double by = Math.pow(1-t,3) * y1 + 3 * t * Math.pow(1-t,2) * y2 + 3* Math.pow(t,2) * (1-t) * y3 + Math.pow(t, 3) * y4;

        return by;

    }
    
    /**
     * gives distance between a value (t) on the curve and the robot
     * @param t used for parametrics, value from (0,1)
     * @return gives distance between robot and specific value on curve (t)
     */
    private double distance(double t){

        double d = Math.sqrt(Math.pow(bezierY(t) - robot[1], 2) + Math.pow(bezierX(t) - robot[0], 2));

        return d;
    }


    /**
     * used to find optimal t value for bezier 
     * draws vertical lines on distance curve and finds intersections
     * finds two lowest values and gets approx local min
     * 
     * @return optimal t value for bezier (from 0 to 1)
     */
    private double desiredT(){

        double height1 = 1000000;
        double height2 = 1000000;

        double higherBound = 0;
        double lowerBound = 1;

        double splineSample = 20;


        double[] intersections = DoubleStream.iterate(0, n -> n + 1 / splineSample).limit(1).toArray();

        for (double i : intersections){
            if(distance(i) < height1){

                height1 = distance(i);
                lowerBound = i;

            }else if(distance(i) < height2){

                height2 = distance(i);
                higherBound = i;

            }
            
        }

        double average = (lowerBound + higherBound) / 2;
        double weight = 0.002 * (height1 - height2) / (lowerBound - higherBound);

        double desiredT = average - weight;

        return desiredT;

    }
    
    /**
     * updates robot once every loop, refines t to fit new pos
     * @param robot
     */
    public void update(double[] robot){
        
        this.robot = robot;

    }
    
    /**
     * gives the angle part of power angle
     * @return angle for bot to move
     */
    public double splineAngle(){

        double yPrime = (bezierY(desiredT() + aligningConstant) - bezierY(desiredT())) / aligningConstant;
        double xPrime = (bezierY(desiredT() + aligningConstant) - bezierY(desiredT())) / aligningConstant;

        double slope = SplineMath.addAngles(Math.atan(yPrime / xPrime), SplineMath.addAngles(bezierX(desiredT()), bezierX(desiredT() + aligningConstant)));
        
        // is the bot above or below the curve, should return 1 or -1
        double botState = bezierY(desiredT()) / Math.abs(bezierY(desiredT()));

        double correction = Math.abs(correctionP * distance(desiredT())) * botState;
    
        return SplineMath.addAngles(correction, slope);

    }

    /**
    * gives power for spline
    * @param p power multiplier
    * @param ff minimum power used (feedForward)
    * @return power for spline
    */
    public double power(double p, double ff){
        
        double power = 1 - desiredT() * p + ff;
        return power;
    
    }
    /**
     * command for finishing spline
     * @return is done or not
     */
    public boolean finishedSplining(){

        if (desiredT() > 1){
            return true;
        }else{
            return false;
        }

    }

}