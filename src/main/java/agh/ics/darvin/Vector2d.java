package agh.ics.darvin;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }

    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    public boolean precedes(Vector2d other){
        return (this.x <= other.x) && (this.y <= other.y);
     }

     public boolean follows(Vector2d other){
        return (this.x >= other.x) && (this.y >= other.y);
     }

     public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x, this.y + other.y);
     }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d upperRight(Vector2d other){
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other){
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    public Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }

    public boolean equals(Object other){
        if (this == other) return true;
        if (!(other instanceof Vector2d)) return false;
        return (this.x == ((Vector2d) other).x) && (this.y == ((Vector2d) other).y);
    }

    public static Vector2d randomVectorInRectangle(Vector2d vector1, Vector2d vector2){
        int leftX = Math.min(vector1.x, vector2.x);
        int rightX = Math.max(vector1.x, vector2.x);

        int leftY = Math.min(vector1.y, vector2.y);
        int rightY = Math.max(vector1.y, vector2.y);

        return new Vector2d((int) ( Math.random() * (rightX - leftX + 1) + leftX)
                , (int) ( Math.random() * (rightY - leftY + 1) + leftY));
    }


}
