package agh.ics.darvin;

import java.util.ArrayList;
import java.util.List;

public class AnimalManager {
    private final List<IPositionChangeObserver> positionObservers = new ArrayList<>();
    private final List<IDeathsObserver> deathsObservers = new ArrayList<>();

    public void addObserver(IPositionChangeObserver observer){
        positionObservers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        positionObservers.remove(observer);
    }

    public void addObserver(IDeathsObserver observer){
        deathsObservers.add(observer);
    }
    public void removeObserver(IDeathsObserver observer){
        deathsObservers.remove(observer);
    }

    public void animalChangedPosition(Vector2d oldPosition, Animal animal){
        for(IPositionChangeObserver observer : positionObservers){
            observer.positionChanged(oldPosition, animal.position, animal);
        }
    }

    public void animalDied(Animal animal){
        for (IDeathsObserver observer: deathsObservers){
            observer.animalDied(animal);
        }
    }
}
