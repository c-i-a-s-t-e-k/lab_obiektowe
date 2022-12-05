package agh.ics.oop;

import agh.ics.oop.gui.GuiElementBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver, IRectanglableMap{
    protected final Map<Vector2d,Animal> animals = new HashMap<>();
    protected final MapVisualizer mapVisualizer = new  MapVisualizer(this);
    abstract public Vector2d getUpperRight();
    abstract public Vector2d getLowerLeft();
    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())){
            animal.addObserver(this);
            animals.put(animal.getPosition(), animal);
            return true;
        }
        else {
            throw new IllegalArgumentException("cannot place animal on position:" + animal.getPosition());
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

    public Map<Vector2d, VBox> getVBoxAnimals(){
        Map<Vector2d, VBox> vBoxAnimals = new HashMap<>();
        for(Animal animal : animals.values()){
            vBoxAnimals.put(animal.getPosition(), (new GuiElementBox( animal)).getVBox());
        }
        return vBoxAnimals;
    }
}
