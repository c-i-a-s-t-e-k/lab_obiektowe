package agh.ics.oop;

public class Grass {
    private Vector2d position;

    public Grass(Vector2d position){
        this.position = position;
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof Grass)
            return this.position.equals(((Grass) other).position);
        return false;
    }
    public Vector2d getPosition() {
        return position;
    }

    public String toString() {
        return "*";
    }
}
