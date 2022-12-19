package agh.ics.oop.engine;

public class Grass implements IMapElement{
    public Grass(Vector2d position){
        this.position = position;
    }
    private Vector2d position;

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
    public String getImageName(){
        return "trawa.jpg";
    }
}
