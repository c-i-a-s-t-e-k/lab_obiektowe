package agh.ics.darvin;

public class Grass extends AbstractMapElement{
    public Grass(Vector2d position){
        this.position = position;
    }

    public String toString() {
        return "*";
    }
    public String getImageName(){
        return "trawa.jpg";
    }
}
