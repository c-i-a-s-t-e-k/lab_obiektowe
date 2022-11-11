package agh.ics.oop;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class GrassField implements IWorldMap{
    private static final Vector2d lowerLeftGrassPosition = new Vector2d(0,0);
    private final List<Grass> grassList = new ArrayList<>();
    private final List<Animal> animalList = new ArrayList<>();
    private final Vector2d upperRightGrassPosition;
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    private void placeGrass(int grassAmount){
        int i = 0;
        while (i < grassAmount){
            Vector2d newPlace = Vector2d.randomVectorInRectangle(lowerLeftGrassPosition, upperRightGrassPosition);

            if (! isOccupied(newPlace)){
                grassList.add(new Grass(newPlace));
                i ++;
            }
        }
    }

    public GrassField(int grassAmount){
        upperRightGrassPosition = new Vector2d((int) Math.sqrt(grassAmount * 10),(int) Math.sqrt(grassAmount * 10));
        placeGrass(grassAmount);
    }

    @Override
    public String toString() {
        Vector2d lowerLeft = grassList.get(0).getPosition();
        Vector2d upperRight = grassList.get(0).getPosition();

        for (Grass grass : grassList) {
            lowerLeft = lowerLeft.lowerLeft(grass.getPosition());
            upperRight = upperRight.upperRight(grass.getPosition());
        }
        for (Animal animal : animalList) {
            lowerLeft = lowerLeft.lowerLeft(animal.getPosition());
            upperRight = upperRight.upperRight(animal.getPosition());
        }
        return mapVisualizer.draw(lowerLeft, upperRight);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (objectAt(position) == null || objectAt(position) instanceof Grass);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

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
    public Object objectAt(Vector2d position) {
        for (Animal animal : animalList){
            if (position.equals(animal.getPosition())) return (Object) animal;
        }
        for (Grass grass : grassList){
            if (position.equals(grass.getPosition())) return (Object) grass;
        }
        return null;
    }
}
