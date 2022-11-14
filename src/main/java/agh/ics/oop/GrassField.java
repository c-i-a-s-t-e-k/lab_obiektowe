package agh.ics.oop;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class GrassField extends AbstractWorldMap{
    private static final Vector2d lowerLeftGrassPosition = new Vector2d(0,0);
    private final List<Grass> grassList = new ArrayList<>();
    private final Vector2d upperRightGrassPosition;

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
    public Vector2d getLowerLeft(){
        Vector2d lowerLeft = grassList.get(0).getPosition();

        for (Grass grass : grassList) {
            lowerLeft = lowerLeft.lowerLeft(grass.getPosition());
        }
        for (Animal animal : animalList) {
            lowerLeft = lowerLeft.lowerLeft(animal.getPosition());
        }
        return lowerLeft;
    }

    @Override
    public Vector2d getUpperRight(){
        Vector2d upperRight = grassList.get(0).getPosition();

        for (Grass grass : grassList) {
            upperRight = upperRight.upperRight(grass.getPosition());
        }
        for (Animal animal : animalList) {
            upperRight = upperRight.upperRight(animal.getPosition());
        }
        return upperRight;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (objectAt(position) == null || objectAt(position) instanceof Grass);
    }


    @Override
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) == null) {
            for (Grass grass : grassList) {
                if (position.equals(grass.getPosition())) return (Object) grass;
            }
            return null;
        }
        else return super.objectAt(position);
    }
}
