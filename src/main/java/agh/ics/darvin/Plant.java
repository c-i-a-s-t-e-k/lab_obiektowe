package agh.ics.darvin;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

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

    @Override
    public Shape get_representation() {
        return new Rectangle(10, 10, Color.color(0,1,0));
    }
}
