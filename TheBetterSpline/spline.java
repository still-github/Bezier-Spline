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
    private double v1;
    private double xr;
    private double yr;
    

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
    
    // used to make the line the bot is projected on, tangent to end angle
    private double lineProjection(double x){
        return slope * (x - waypoint.x) + waypoint.y;
    }
    

    //closest x value 
    public double optimalX(){
        return (c2 * Math.pow(v1, 2) + (yr * (c1 - c2) - (c1 + c2) * v2) * v1 - yr * (c1 - c2) * v2 + xr * Math.pow(c1 - c2, 2)
        + c1 * Math.pow(v2 , 2)) / (Math.pow(v1, 2) - 2 * v2 * v1 + Math.pow(c1 , 2) - 2 * c1 * c2 + Math.pow(c2, 2) + Math.pow(v2 , 2));
    }

    //distance from optimal x on the line to waypoint
    private double distanceOnLine(){
        return Math.sqrt(Math.pow(c1 - optimalX(), 2) + Math.pow(v1 - lineProjection(optimalX()), 2));
    }
    
    //how far the bot is from the tangent line
    private double fromLine(){
        return Math.sqrt(Math.pow(xr - optimalX(), 2) + Math.pow(yr - lineProjection(optimalX()), 2));
    }

    
    //desiredT for point it's correcting to
    private double desiredT(){
        return 1 - fromLine() / distanceOnLine();
    }

    //distance from projected point on the line to the waypoint
    private double projectedDistance(){
        return Math.sqrt(Math.pow(desiredT() * (c1 - optimalX()) + optimalX() - c1, 2)
         + Math.pow(desiredT() * (v1 - lineProjection(optimalX())) + lineProjection(optimalX()) - v1, 2));
    }

    /**
     * makes drive vector to follow path
     * @return vector as vector object
     */
    public Vector driveVector(){
        double vx;
        double vy;
        
        //makes sure it doesn't move away from the point, goes normal in this case
        if(distanceOnLine() > projectedDistance()){
            vx = desiredT() * (c1 - optimalX()) + optimalX() - xr;
            vy = desiredT() * (v1 - lineProjection(optimalX())) + lineProjection(optimalX()) - yr;
        }else{
            vx = optimalX() - xr;
            vy = lineProjection(optimalX()) - yr;
        }
        Vector unshrunkVector =  new Vector(vx, vy);

        return unshrunkVector.clipMagnitude(1);
    }


    /**
     * updates with current robot pose
     * @param robotPose current robot pose
     */
    public void update(RobotPose robotPose){
        yr = this.robotPose.y;
        xr = this.robotPose.x;
    }

}

