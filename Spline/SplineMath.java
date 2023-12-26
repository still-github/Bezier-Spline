package Spline;

public class SplineMath {
    
    /**
     * gives angle from -π / 2 to π / 2
     * @param slope input slope 
     * @return get angle out
     */
    public double slopeToAngle(double slope){
        double angle = Math.atan(slope);
        return angle;
    }

}
