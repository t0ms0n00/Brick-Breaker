package agh.cs;

import java.util.Objects;

public class Vector2D {
    final double x;
    final double y;

    public Vector2D(double x,double y){
        this.x=x;
        this.y=y;
    }

    public double getX(){
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public String toString(){
        return ("("+this.x+", "+this.y+")");
    }

    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    public Vector2D upperRight(Vector2D other){
        double x;
        double y;
        if (this.x>=other.x) x=this.x;
        else x=other.x;
        if (this.y>=other.y) y=this.y;
        else y=other.y;
        Vector2D score=new Vector2D(x,y);
        return score;
    }

    public Vector2D lowerLeft(Vector2D other){
        double x;
        double y;
        if (this.x>=other.x) x=other.x;
        else x=this.x;
        if (this.y>=other.y) y=other.y;
        else y=this.y;
        Vector2D score=new Vector2D(x,y);
        return score;
    }

    public Vector2D add(Vector2D other){
        double x=this.x+other.x;
        double y=this.y+other.y;
        Vector2D score=new Vector2D(x,y);
        return score;
    }

    public Vector2D subtract(Vector2D other){
        double x=this.x-other.x;
        double y=this.y-other.y;
        Vector2D score=new Vector2D(x,y);
        return score;
    }

    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Vector2D)) return false;
        Vector2D that = (Vector2D) other;
        if (that.x==this.x && that.y==this.y) return true;
        else return false;
    }

}
