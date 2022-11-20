package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{
    protected final Map<Vector2d,Animal> animals = new HashMap<>();
    protected final MapVisualizer mapVisualizer = new  MapVisualizer(this);
    abstract public Vector2d getUpperRight();
    abstract public Vector2d getLowerLeft();
    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(), animal);
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
        return animals.get(position);
    }

    public String toString() {
        return mapVisualizer.draw(getLowerLeft(), getUpperRight());
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        animals.put(newPosition, animals.remove(oldPosition));
    }
}
