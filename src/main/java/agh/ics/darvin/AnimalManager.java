package agh.ics.darvin;

import java.util.ArrayList;
import java.util.List;

public class AnimalManager implements IManager{
    private final List<IPositionChangeObserver> positionObservers = new ArrayList<>();
    private final List<IDeathsObserver> deathsObservers = new ArrayList<>();

    public void addObserver(Object observer){
        if (observer instanceof IPositionChangeObserver)
            positionObservers.add((IPositionChangeObserver) observer);
        if (observer instanceof IDeathsObserver) {
            deathsObservers.add((IDeathsObserver) observer);
        }
    }
    public void removeObserver(Object observer){
        if (observer instanceof IPositionChangeObserver)
            positionObservers.remove((IPositionChangeObserver) observer);
        if (observer instanceof IDeathsObserver) {
            deathsObservers.remove((IDeathsObserver) observer);
        }
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
