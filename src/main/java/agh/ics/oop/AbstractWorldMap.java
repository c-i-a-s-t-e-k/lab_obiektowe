package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap{
    protected final List<Animal> animalList = new ArrayList<>();
    protected final MapVisualizer mapVisualizer = new  MapVisualizer(this);
    abstract public Vector2d getUpperRight();
    abstract public Vector2d getLowerLeft();
    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animalList.add(animal);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    public Object objectAt(Vector2d position){
        for (Animal animal : animalList){
            if (position.equals(animal.getPosition())) return (Object) animal;
        }
        return null;
    }

    public String toString() {
        return mapVisualizer.draw(getLowerLeft(), getUpperRight());
    }

    public boolean canMoveTo(Vector2d position){
        return !isOccupied(position);
    }
}
