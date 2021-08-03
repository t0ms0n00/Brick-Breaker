package map_elements;

import agh.cs.MoveDirection;
import agh.cs.Vector2D;

public class Ball{

    private Vector2D position;
    private int r;
    private MoveDirection move=MoveDirection.UPRIGHT;
    private int speed=5;

    public Ball(double x,double y,int r){
        this.position=new Vector2D(x,y);
        this.r=r;
    }

    public int getR() {
        return this.r;
    }

    public double getXPos(){
        return this.position.getX();
    }

    public double getYPos(){
        return this.position.getY();
    }

    public MoveDirection getMove(){
        return this.move;
    }

    public void setMove(MoveDirection newMove){
        this.move=newMove;
    }

    public void setSpeed(int speed){
        this.speed=speed;
    }

    public void move(){
        this.position=this.position.add(this.move.toVector(speed));
    }
}
