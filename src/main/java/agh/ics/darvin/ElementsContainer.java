package agh.ics.darvin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElementsContainer implements IPositionChangeObserver{
    private final Map<Vector2d, Grass> plants = new HashMap<>();
    private final Map<Vector2d, List<Animal>> animals = new HashMap<>();

    private void addAnimal(Animal animal){
        if(! animals.containsKey(animal.getPosition()))
            animals.put(animal.getPosition(), new ArrayList<Animal>());
        animals.get(animal.getPosition()).add(animal);
    }
    public void addElement(IMapElement mapElement){
        if (mapElement instanceof Grass){
            plants.put(mapElement.getPosition(), (Grass) mapElement);
        } else if (mapElement instanceof Animal) {
            this.addAnimal((Animal) mapElement);
        }
    }

    public void removeAnimal(Animal animal, Vector2d position){
        animals.get(position).remove(animal);
        if(animals.get(position).isEmpty()) animals.remove(animal.getPosition());
    }
    public void removePlant(Grass plant){
        plants.remove(plant.getPosition());
    }

    public void removeElement(IMapElement mapElement){
        if (mapElement instanceof Grass){
            this.removePlant((Grass) mapElement);
        }
        else if (mapElement instanceof Animal){
            this.removeAnimal((Animal) mapElement, mapElement.getPosition());
        }
    }

    public Animal[] getAnimals(Vector2d position){
        if (animals.containsKey(position))
            return animals.get(position).toArray(new Animal[0]);
        else return new Animal[0];
    }
    public Grass getPlant(Vector2d position){
        return plants.getOrDefault(position, null);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal){
        removeAnimal(animal, oldPosition);
        addAnimal(animal);
    }
}
