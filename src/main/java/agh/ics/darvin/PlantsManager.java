package agh.ics.darvin;

import java.util.ArrayList;
import java.util.List;

public class PlantsManager {
    static final List<IPlantObserver> observers = new ArrayList<>();
    public static void addObserver(IPlantObserver observer){
        observers.add(observer);
    }
    public static void removeObserver(IPlantObserver observer){
        observers.remove(observer);
    }
    public void alreadyRemoved(Plant plant){
        for (IPlantObserver observer : observers){
            observer.plantRemoved(plant.position);
        }
    }
    public void alreadyAdded(Plant plant){
        for (IPlantObserver observer : observers){
            observer.plantAdd(plant.position);
        }
    }
}
