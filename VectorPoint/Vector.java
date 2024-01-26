package VectorPoint;

public class Vector {
    
    private double x;
    private double y;

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getAngle(){
        return Math.atan2(x , y);
    }

    public double getPower(){
        return Math.hypot(x, y);
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void addVector(Vector inputVector){
        x += inputVector.getX();
        y += inputVector.getY();
    }


}
