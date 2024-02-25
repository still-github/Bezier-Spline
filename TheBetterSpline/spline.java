package TheBetterSpline;

import MathStuff.*;

public class Spline {
    private Point waypoint;
    private double angle;
    private RobotPose robotPose;
    

    /**
     * makes spline object
     * 
     * @param waypoint target point 
     * @param angle of approch [-π, π] also acts as slope instead of angle
     */
    public Spline(Point waypoint, double angle, RobotPose robotPose){
        this.waypoint = waypoint;
        this.angle = angle;
        this.robotPose = robotPose;
    }

    //slope between waypoint and 
    private double slope = Math.abs(angle) == Math.PI / 2 ? slope = Math.tan(angle + 0.00000001) : Math.tan(angle);

    
    // used to make the line the bot is projected on
    
    private double lineProjection(double x){
        return slope * (x - waypoint.x) + waypoint.y;
    
    }

    private Point slopePoint = new Point(waypoint.x + Math.cos(angle), waypoint.y + Math.sin(angle));

    //doing this so I can copy off desmos better
    private double c1 = waypoint.x;
    private double v1 = waypoint.y;
    private double c2 = slopePoint.x;
    private double v2 = slopePoint.y;
    private double xr = robotPose.x;
    private double yr = robotPose.y;

    
    // a tad bit long 
    private double optimalX = (c2 * Math.pow(v1, 2) + (yr * (c1 - c2) - (c1 + c2) * v2) * v1 - yr * (c1 - c2) * v2 + xr * Math.pow(c1 - c2, 2)
     + c1 * Math.pow(v2 , 2)) / (Math.pow(v1, 2) - 2 * v2 * v1 + Math.pow(c1 , 2) - 2 * c1 * c2 + Math.pow(c2, 2) + Math.pow(v2 , 2));


    //distance along line 
    private double distanceLine = Math.sqrt(Math.pow(c1 - optimalX, 2) + Math.pow(v1 - lineProjection(optimalX), 2));

    //distance from projected line
    private double fromLine = Math.sqrt(Math.pow(xr - c1, 2) + Math.pow(yr - v1, 2));

    //desiredT??
    private double desiredT = 1 - distanceLine / fromLine;

    public Vector driveVector(){
        double vx = desiredT * (c1 - optimalX) + optimalX - xr;
        double vy = desiredT * (v1 - lineProjection(optimalX)) + lineProjection(optimalX) - yr
        
        return new Vector(vx, vy);

    }

    public void update(RobotPose robotPose){
        this.robotPose = robotPose;
    }

}
