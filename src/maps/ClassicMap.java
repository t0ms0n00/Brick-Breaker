package maps;

import PowerUps.PowerUps;
import agh.cs.MoveDirection;
import agh.cs.Vector2D;
import map_elements.Ball;
import map_elements.Brick;
import map_elements.Platform;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

public class ClassicMap implements KeyListener {

    private int width;
    private int height;
    private ArrayList<Ball> balls;
    private Platform platform;
    private int numOfBricksCols;
    private int numOfBricksRows;
    private ArrayList<Brick> bricks=new ArrayList<>();
    private JFrame mapVis=new JFrame();
    private ArrayList<PowerUps> powerUpsToCatch = new ArrayList<>();

    public ClassicMap(int width,int height,ArrayList<Ball> balls,int cols,int rows){
        this.width=width;
        this.height=height;
        this.balls=balls;
        this.platform=new Platform(this.width/2,this.height*0.9,100);
        this.numOfBricksCols=cols;
        this.numOfBricksRows=rows;

        this.mapVis.setVisible(true);
        this.mapVis.getContentPane().setBackground(Color.BLACK);
        this.mapVis.setBounds(100,100,this.width,this.height);
        this.mapVis.setResizable(false);
        this.mapVis.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mapVis.getContentPane().setLayout(null);
        this.mapVis.addKeyListener(this);

        double brickWidth=(this.width*3)/(5*this.numOfBricksCols);
        double brickHeight=(this.height)/(5*this.numOfBricksRows);
        for(int i=0;i<this.numOfBricksRows;i++) {
            for (int j = 0; j < this.numOfBricksCols; j++) {
                double brickX = this.width/5 + j * brickWidth + brickWidth/2;
                double brickY = this.height/10 + i * brickHeight + brickHeight/2;
                Random random = new Random();
                int hp =1+ Math.abs(random.nextInt() % 4);
                Brick brick = new Brick(brickX, brickY, brickWidth, brickHeight, hp);
                this.bricks.add(brick);
            }
        }
        this.paint();
    }

    public int getAmountOfBalls(){
        return this.balls.size();
    }

    public int getAmountOfBricks(){
        return this.bricks.size();
    }

    public void paint(){
        this.mapVis.getContentPane().removeAll();

        double brickWidth=(this.width*3)/(5*this.numOfBricksCols);
        double brickHeight=(this.height)/(5*this.numOfBricksRows);

        for(Brick brick:bricks){
            JPanel brickPanel = new JPanel();
            brickPanel.setBackground(Color.RED);
            brickPanel.setBorder(new LineBorder(Color.BLACK));
            brickPanel.setBounds((int) (brick.getXPos()-brickWidth/2), (int) (brick.getYPos()-brickHeight/2),
                    (int) brickWidth,(int) brickHeight);
            JLabel label=new JLabel();
            label.setText(String.valueOf(brick.getHp()));
            brickPanel.add(label);
            this.mapVis.add(brickPanel);
        }

        JPanel platformPanel=new JPanel();
        platformPanel.setBackground(Color.CYAN);
        platformPanel.setBorder(new LineBorder(Color.WHITE));
        platformPanel.setBounds((int)this.platform.getXPos()-this.platform.getWidth()/2,(int)this.platform.getYPos(), this.platform.getWidth(), 5);
        this.mapVis.add(platformPanel);

        for(Ball ball:this.balls){
            JPanel ballPanel=new JPanel();
            ballPanel.setBackground(Color.WHITE);
            ballPanel.setBorder(new LineBorder(Color.RED));
            ballPanel.setBounds((int)ball.getXPos()-ball.getR(),(int)ball.getYPos()-ball.getR()
                    ,ball.getR()*2,ball.getR()*2);
            this.mapVis.getContentPane().add(ballPanel);
        }

        ArrayList <PowerUps> toRemove = new ArrayList<>();
        for (PowerUps powerUp:this.powerUpsToCatch){
            if(powerUp.getPosition().getX()==0 && powerUp.getPosition().getY()==0){
                toRemove.add(powerUp);
                continue;
            }
            powerUp.changePos();
            JPanel powerUpPanel=new JPanel();
            int state = powerUp.getNumber();
            switch (state){
                case 1:
                case 3:
                case 5:
                    powerUpPanel.setBackground(Color.GREEN);
                    powerUpPanel.setBounds((int)powerUp.getPosition().getX()-10,(int)powerUp.getPosition().getY()-10,
                            20,20);
                    break;
                case 2:
                case 4:
                    powerUpPanel.setBackground(Color.RED);
                    powerUpPanel.setBounds((int)powerUp.getPosition().getX()-10,(int)powerUp.getPosition().getY()-10,
                            20,20);
                    break;
                default:
                    break;
            }
            this.mapVis.getContentPane().add(powerUpPanel);
        }

        for(PowerUps rm:toRemove){
            this.powerUpsToCatch.remove(rm);
        }

        this.mapVis.revalidate();
        this.mapVis.repaint();

    }

    public void checkCollisions(){
        ArrayList<Ball> ballsToRm=new ArrayList<>();
        ArrayList<Brick> bricksToRm=new ArrayList<>();
        for(Ball ball: this.balls){
            ball.move();
            if(ball.getXPos()+ball.getR()>=this.width){   /// prawa sciana
                if(ball.getMove()== MoveDirection.DOWNRIGHT) ball.setMove(MoveDirection.DOWNLEFT);
                else ball.setMove(MoveDirection.UPLEFT);
            }
            if(ball.getXPos()-ball.getR()<=0){            /// lewa sciana
                if(ball.getMove()== MoveDirection.UPLEFT) ball.setMove(MoveDirection.UPRIGHT);
                else ball.setMove(MoveDirection.DOWNRIGHT);
            }
            if(ball.getYPos()-ball.getR()<=0){    /// gorna sciana
                if(ball.getMove()==MoveDirection.UPRIGHT) ball.setMove(MoveDirection.DOWNRIGHT);
                else ball.setMove(MoveDirection.DOWNLEFT);
            }
            if(platform.getYPos()-ball.getYPos()<=ball.getR() &&    /// platforma
            platform.getYPos()-ball.getYPos()>=0 &&
            ball.getXPos()>=platform.getXPos()-platform.getWidth()/2 &&
            ball.getXPos()<= platform.getXPos()+ platform.getWidth()/2){
                if(ball.getMove()==MoveDirection.DOWNRIGHT) ball.setMove(MoveDirection.UPRIGHT);
                else ball.setMove(MoveDirection.UPLEFT);
            }
            if(ball.getYPos()+ball.getR()>=this.height){    /// dolna sciana
                ballsToRm.add(ball);
            }
        }
        for(Brick brick:this.bricks){
            for(Ball ball:this.balls){
                /// obija z dolu
                if(ball.getYPos()-(brick.getYPos()+brick.getHeight()/2)<=ball.getR() &&
                    ball.getYPos()-(brick.getYPos()+brick.getHeight()/2)>=0 &&
                    ball.getXPos()>=brick.getXPos()-brick.getWidth()/2 &&
                    ball.getXPos()<=brick.getXPos()+brick.getWidth()/2){
                    if(ball.getMove()==MoveDirection.UPRIGHT) ball.setMove(MoveDirection.DOWNRIGHT);
                    else ball.setMove(MoveDirection.DOWNLEFT);
                    brick.decreaseHp();
                    if(brick.getHp()<=0) {
                        bricksToRm.add(brick);
                        if (brick.getPowerUp()!=null){
                            this.powerUpsToCatch.add(brick.getPowerUp());
                        }
                    }
                }
                /// obija z gory
                if((brick.getYPos()-brick.getHeight()/2)-ball.getYPos()<=ball.getR() &&
                        (brick.getYPos()-brick.getHeight()/2)-ball.getYPos()>=0 &&
                        ball.getXPos()>=brick.getXPos()-brick.getWidth()/2 &&
                        ball.getXPos()<=brick.getXPos()+brick.getWidth()/2){
                    if(ball.getMove()==MoveDirection.DOWNRIGHT) ball.setMove(MoveDirection.UPRIGHT);
                    else ball.setMove(MoveDirection.UPLEFT);
                    brick.decreaseHp();
                    if(brick.getHp()<=0) {
                        bricksToRm.add(brick);
                        if (brick.getPowerUp()!=null){
                            this.powerUpsToCatch.add(brick.getPowerUp());
                        }
                    }
                }
                /// obija z lewej
                if(brick.getXPos()-brick.getWidth()/2-ball.getXPos()<=ball.getR() &&
                    brick.getXPos()-brick.getWidth()/2-ball.getXPos()>=0 &&
                    ball.getYPos()>=brick.getYPos()-brick.getHeight()/2 &&
                    ball.getYPos()<=brick.getYPos()+brick.getHeight()){
                    if(ball.getMove()== MoveDirection.DOWNRIGHT) ball.setMove(MoveDirection.DOWNLEFT);
                    else ball.setMove(MoveDirection.UPLEFT);
                    brick.decreaseHp();
                    if(brick.getHp()<=0) {
                        bricksToRm.add(brick);
                        if (brick.getPowerUp()!=null){
                            this.powerUpsToCatch.add(brick.getPowerUp());
                        }
                    }
                }
                /// obija z prawej
                if(ball.getXPos()-(brick.getXPos()+brick.getWidth()/2)<=ball.getR() &&
                        ball.getXPos()-(brick.getXPos()+brick.getWidth()/2)>=0 &&
                        ball.getYPos()>=brick.getYPos()-brick.getHeight()/2 &&
                        ball.getYPos()<=brick.getYPos()+brick.getHeight()){
                    if(ball.getMove()== MoveDirection.UPLEFT) ball.setMove(MoveDirection.UPRIGHT);
                    else ball.setMove(MoveDirection.DOWNRIGHT);
                    brick.decreaseHp();
                    if(brick.getHp()<=0) {
                        bricksToRm.add(brick);
                        if (brick.getPowerUp()!=null){
                            this.powerUpsToCatch.add(brick.getPowerUp());
                        }
                    }
                }
            }
        }
        for(Ball rmBall:ballsToRm){
            this.balls.remove(rmBall);
        }
        for(Brick rmBrick:bricksToRm){
            this.bricks.remove(rmBrick);
        }
    }

    public void checkCatchedPowerUps(){

        ArrayList<PowerUps> toRm=new ArrayList<>();

        for(PowerUps powerUp: this.powerUpsToCatch){
            if(platform.getYPos()-powerUp.getPosition().getY()<=10 &&    /// platforma
                    platform.getYPos()-powerUp.getPosition().getY()>=0 &&
                    powerUp.getPosition().getX()>=platform.getXPos()-platform.getWidth()/2 &&
                    powerUp.getPosition().getX()<= platform.getXPos()+ platform.getWidth()/2){
                toRm.add(powerUp);
                int state = powerUp.getNumber();
                switch (state){
                    case 1:
                        this.platform.setSpeed(this.platform.getSpeed()+3);
                        break;
                    case 2:
                        this.platform.setSpeed(this.platform.getSpeed()-3);
                        break;
                    case 3:
                        this.platform.setWidth(this.platform.getWidth()+25);
                        break;
                    case 4:
                        this.platform.setWidth(this.platform.getWidth()-25);
                        break;
                    case 5:
                        this.balls.add(new Ball(150,450,10));
                        break;
                    default:
                        break;
                }
            }
        }
        for(PowerUps toRemove:toRm){
            this.powerUpsToCatch.remove(toRemove);
        }
    }

    public void closeFrame(){
        this.mapVis.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.mapVis.dispatchEvent(new WindowEvent(this.mapVis, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()){
            case 'a': this.platform.setPosition(new Vector2D(-this.platform.getSpeed(),0));
                        break;
            case 'd': this.platform.setPosition(new Vector2D(this.platform.getSpeed(),0));
                        break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case 37: this.platform.setPosition(new Vector2D(-this.platform.getSpeed(),0));
                break;
            case 39: this.platform.setPosition(new Vector2D(this.platform.getSpeed(),0));
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
