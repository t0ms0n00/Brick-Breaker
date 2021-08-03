package agh.cs;

public enum MoveDirection {
    UPLEFT,
    UPRIGHT,
    DOWNLEFT,
    DOWNRIGHT;

    public Vector2D toVector(int speed){
        switch (this){
            case UPLEFT:return new Vector2D(-speed,-speed);
            case UPRIGHT:return new Vector2D(speed,-speed);
            case DOWNLEFT:return new Vector2D(-speed,speed);
            case DOWNRIGHT:return new Vector2D(speed,speed);
            default:return new Vector2D(0,0);
        }
    }
}
