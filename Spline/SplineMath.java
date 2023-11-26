package Spline;

public class SplineMath {
    /**
     * adds angles without going over 2π
     * @param angleOne first angle
     * @param angleTwo second angle
     * @return added angles 
     */
    public static double addAngles(double angleOne, double angleTwo){

        double trueAngle = (angleOne + angleTwo) % 2 * Math.PI;

        return trueAngle;

    }


    /**
     * used to convert arctan to one angle
     * @param currentX current x bezier 
     * @param nextX x bezier + small number
     * @return number to add to convert to correct angle
     */
    public static double angleQuadrant(double currentX, double nextX){

        if(currentX < nextX){
            return 0;
        }else{
            return Math.PI;
        }

    }


}
