package VectorPoint;

public class Point {
    
    double x;
    double y;


    /**
     * makes a point
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     */
    public Point(double x, double y){
        this.x = x;
        this.y = y;

    }


    /**
     * the x value of the point
     * @return x coordinate as a double
     */
    public double getX(){
        return x;
    }


    /**
     * the y value of the point
     * @return y coordinate as a double
     */
    public double getY(){
        return y;
    }


    /**
     * moves point by a vector
     * @param translation vector that shifts the point
     */
    public void translate(Vector translation){
        x += translation.getX();
        y += translation.getY();
    }

    /**
     * gets distance from input point
     * @param inputPoint point to get distance from
     * @return distance from input point
     */
    public double distance(Point inputPoint){ 
        return Math.hypot((this.x - inputPoint.getX()), this.y - inputPoint.getY()); 
    }


}
