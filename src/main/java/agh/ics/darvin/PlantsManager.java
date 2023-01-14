package agh.ics.darvin;

import java.util.ArrayList;
import java.util.List;

public class PlantsManager implements IManager {
    static final List<IPlantObserver> observers = new ArrayList<>();

    public void addObserver(Object observer) {
        if (observer instanceof IPlantObserver)
            observers.add((IPlantObserver) observer);
    }

    public void removeObserver(Object observer) {
        if (observer instanceof IPlantObserver)
            observers.remove((IPlantObserver) observer);
    }

    public void alreadyRemoved(Plant plant) {
        for (IPlantObserver observer : observers) {
            observer.plantRemoved(plant.position);
        }
    }

    public void alreadyAdded(Plant plant) {
        for (IPlantObserver observer : observers) {
            observer.plantAdd(plant.position);
        }
    }
}
