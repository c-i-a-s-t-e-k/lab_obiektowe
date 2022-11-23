package agh.ics.oop;

public class Grass{
    public Grass(Vector2d position){
        this.position = position;
    }
    protected Vector2d position;

    public Vector2d getPosition(){
        return position;
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof Grass)
            return this.position.equals(((Grass) other).position);
        return false;
    }

    public String toString() {
        return "*";
    }
}
