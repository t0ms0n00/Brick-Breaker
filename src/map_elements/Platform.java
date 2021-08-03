package map_elements;

import agh.cs.Vector2D;

public class Platform {

    private Vector2D position;
    private int width;
    private int speed=15;

    public Platform(double x,double y,int width){
        this.position=new Vector2D(x,y);
        this.width=width;
    }

    public double getXPos(){
        return this.position.getX();
    }

    public double getYPos(){
        return this.position.getY();
    }

    public int getWidth(){
        return this.width;
    }

    public int getSpeed(){
        return this.speed;
    }

    public void setSpeed(int newSpeed){
        this.speed=Math.max(1,newSpeed);
    }

    public void setWidth(int newWidth){
        this.width=Math.max(1,newWidth);
    }

    public void setPosition(Vector2D newPosition){
        this.position=this.position.add(newPosition);
    }
}
