package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap{
    public final int width;
    public final int height;
    private static final Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    private final List<Animal> animals = new ArrayList<>();

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.upperRight = new Vector2d(this.width, this.height);
    }


    public boolean canMoveTo(Vector2d position) {
        return (position.precedes(upperRight) && (position.follows(lowerLeft)) && (!isOccupied(position)));
    }

    public boolean place(Animal animal){
        if (canMoveTo(animal.getPosition())){
            animals.add(animal);
            return true;
        }
        else {
        return false;
        }
    }

    public boolean isOccupied(Vector2d position){
        for (Animal animal : animals){
            if (position.equals(animal.getPosition())) return true;
        }
        return false;
    }

    public Object objectAt(Vector2d position){
        for (Animal animal : animals){
            if (position.equals(animal.getPosition())) return (Object) animal;
        }
        return null;
    }

    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
