package TheBetterSpline;

import MathStuff.*;

public class Spline {
    private Point waypoint;
    private RobotPose robotPose;
    private double slope;
    private Point slopePoint;
    private double c1;
    private double c2;
    private double v2;
    private double xr;
    private double yr;
    private double v1;
    

    /**
     * creates spline v4 object
     * @param waypoint desired endpoint 
     * @param angle angle to be tangent with at the end
     * @param robotPose starting robot pos
     */
    public Spline(Point waypoint, double angle, RobotPose robotPose){
        this.waypoint = waypoint;
        this.robotPose = robotPose;

        slope = Math.abs(angle) == Math.PI / 2 ? slope = Math.tan(angle + 0.00000001) : Math.tan(angle);

        slopePoint = new Point(waypoint.x + Math.cos(angle), waypoint.y + Math.sin(angle));

        c1 = waypoint.x;
        v1 = waypoint.y;
        c2 = slopePoint.x;
        v2 = slopePoint.y;
        xr = robotPose.x;
        yr = robotPose.y;

    }
    
    // used to make the line the bot is projected on
    
    private double lineProjection(double x){
        return slope * (x - waypoint.x) + waypoint.y;
    }
    
    private double optimalX(){
        return (c2 * Math.pow(v1, 2) + (yr * (c1 - c2) - (c1 + c2) * v2) * v1 - yr * (c1 - c2) * v2 + xr * Math.pow(c1 - c2, 2)
        + c1 * Math.pow(v2 , 2)) / (Math.pow(v1, 2) - 2 * v2 * v1 + Math.pow(c1 , 2) - 2 * c1 * c2 + Math.pow(c2, 2) + Math.pow(v2 , 2));
    }

    private double distanceOnLine(){
        return Math.sqrt(Math.pow(c1 - optimalX(), 2) + Math.pow(v1 - lineProjection(optimalX()), 2));
    }
    
    private double fromLine(){
        return Math.sqrt(Math.pow(xr - optimalX(), 2) + Math.pow(yr - lineProjection(optimalX()), 2));
    }

    //desiredT??
    public double desiredT(){
        return 1 - fromLine() / distanceOnLine();
    }

    /**
     * makes drive vector to follow path
     * @return vector as vector object
     */
    public Vector driveVector(){
        double vx = desiredT() * (c1 - optimalX()) + optimalX() - xr;
        double vy = desiredT() * (v1 - lineProjection(optimalX())) + lineProjection(optimalX()) - yr;
        
        Vector unshrunkVector =  new Vector(vx, vy);

        return unshrunkVector;

    }


    /**
     * updates with current robot pose
     * @param robotPose current robot pose
     */
    public void update(RobotPose robotPose){
        this.robotPose = robotPose;
        yr = this.robotPose.y;
        xr = this.robotPose.x;
    }

}

