package map_elements;

import PowerUps.PowerUps;
import agh.cs.Vector2D;

import java.util.Random;

public class Brick {

    private Vector2D position;
    private double width;
    private double height;
    private int hp;
    private PowerUps powerUp;

    public Brick(double x,double y,double width,double height,int hp){
        this.position=new Vector2D(x,y);
        this.width=width;
        this.height=height;
        this.hp=hp;
        this.setPowerUp();
    }

    public void setPowerUp(){
        Random random=new Random();
        int power=Math.abs(random.nextInt(10));
        this.powerUp=new PowerUps(this.position,power);
    }

    public PowerUps getPowerUp(){
        return this.powerUp;
    }

    public double getXPos(){
        return this.position.getX();
    }

    public double getYPos(){
        return this.position.getY();
    }

    public double getWidth(){
        return this.width;
    }

    public double getHeight(){
        return this.height;
    }

    public int getHp(){
        return this.hp;
    }

    public void decreaseHp(){
        this.hp-=1;
    }
}
