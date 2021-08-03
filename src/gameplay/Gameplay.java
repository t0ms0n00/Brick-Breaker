package gameplay;

import ending.Lost;
import ending.Win;
import map_elements.Ball;
import maps.ClassicMap;

import java.util.ArrayList;

public class Gameplay{
    private Ball ball1=new Ball(150,450,10);
    private ArrayList<Ball> balls=new ArrayList<>();
    private ClassicMap map;

    public Gameplay(){
        balls.add(ball1);
        map=new ClassicMap(600,750, balls,8,5);
    }

    public void run() throws InterruptedException {
        while(map.getAmountOfBalls()>0 && map.getAmountOfBricks()>0){
            map.paint();
            map.checkCollisions();
            map.checkCatchedPowerUps();
            Thread.sleep(20);
        }
        map.closeFrame();
        if(map.getAmountOfBalls()<=0){
            Lost lost=new Lost();
            lost.draw();
        }
        else{
            Win win=new Win();
            win.draw();
        }
    }
}
