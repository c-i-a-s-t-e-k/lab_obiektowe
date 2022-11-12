package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractWorldMap implements IWorldMap{
    protected final List<Animal> animalList = new ArrayList<>();
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
}
