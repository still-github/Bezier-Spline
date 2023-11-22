package Spline;

public class Spline{

    private double[] entrance, entranceControl, exit, exitControl;

    private double robot[];

    public Spline(double[] entrance, double[] entranceControl, double[] exit, double[] exitControl, double correctionP){
        
        this.entrance = entrance;
        this.entranceControl = entranceControl;
        this.exit = exit;
        this.exitControl = exitControl;
    }

    private double bezierX(double t){
        
        double x1 = entrance[0];
        double x2 = entranceControl[0]; 
        double x3 = exit[0];
        double x4 = exitControl[0];

        double bx = Math.pow(1-t,3) * x1 + 3 * t * Math.pow(1-t,2) * x2 + 3* Math.pow(t,2) * (1-t) * x3 + Math.pow(t, 3) * x4;

        return bx;

    }

    private double bezierY(double t){
        
        double y1 = entrance[0];
        double y2 = entranceControl[0]; 
        double y3 = exit[0];
        double y4 = exitControl[0];

        double by = Math.pow(1-t,3) * y1 + 3 * t * Math.pow(1-t,2) * y2 + 3* Math.pow(t,2) * (1-t) * y3 + Math.pow(t, 3) * y4;

        return by;

    }
    
    private double distance(double t){

        double d = Math.sqrt(Math.pow(bezierY(t) - robot[1], 2) + Math.pow(bezierX(t) - robot[0], 2));

        return d;
    }

    public void update(double[] robot){
        this.robot = robot;

    }

    private double desiredT(){
        
        double t = 0;

        if(distance(t) < distance(t + 0.001)){
            t -= 0.01;

        }else{
            t += 0.001;
        }


    }


}