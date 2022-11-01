package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    public final int width;
    public final int height;
    private List<Animal> animals = new ArrayList<>();

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
    }

    public boolean canMoveTo(Vector2d position){
        return false;
    }

    public boolean place(Animal animal){
        return false;
    }

    public boolean isOccupied(Vector2d position){
        return false;
    }

    public Object objectAt(Vector2d position){
        return new Object();
    }
}
