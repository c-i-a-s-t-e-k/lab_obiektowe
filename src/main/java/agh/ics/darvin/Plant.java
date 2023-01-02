package agh.ics.darvin;

import java.util.ArrayList;
import java.util.List;

public class Plant extends AbstractMapElement{

    public Plant(Vector2d position){
        this.position = position;
    }

    public String toString() {
        return "*";
    }
    public String getImageName(){
        return "trawa.jpg";
    }
}
