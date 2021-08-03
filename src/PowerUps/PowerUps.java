package PowerUps;

import agh.cs.Vector2D;

public class PowerUps {
    Vector2D position;
    int number;

    public PowerUps(Vector2D position,int x){
        this.number=x;
        switch(this.number){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                this.position=position;
                break;
            default:
                this.position=new Vector2D(0,0);
        }
    }

    public int getNumber(){
        return this.number;
    }

    public void changePos(){
        this.position=this.position.add(new Vector2D(0,5));
    }

    public Vector2D getPosition(){
        return this.position;
    }
}
