package agh.ics.oop;

import java.lang.Math;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GrassField extends AbstractWorldMap{
    private static final Vector2d lowerLeftGrassPosition = new Vector2d(0,0);
    private final Map<Vector2d,Grass> grasses = new HashMap<>();
    private final Vector2d upperRightGrassPosition;

    private void placeGrass(int grassAmount){
        int i = 0;
        while (i < grassAmount){
            Vector2d newPlace = Vector2d.randomVectorInRectangle(lowerLeftGrassPosition, upperRightGrassPosition);

            if (! isOccupied(newPlace)){
                grasses.put(newPlace, new Grass(newPlace));
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
        Grass[] grassTab = grasses.values().toArray(new Grass[0]);
        Vector2d lowerLeft = grassTab[0].getPosition();

        for (Grass grass : grassTab) {
            lowerLeft = lowerLeft.lowerLeft(grass.getPosition());
        }
        for (Animal animal : animals.values()) {
            lowerLeft = lowerLeft.lowerLeft(animal.getPosition());
        }
        return lowerLeft;
    }

    @Override
    public Vector2d getUpperRight(){
        Grass[] grassTab = grasses.values().toArray(new Grass[0]);
        Vector2d upperRight = grassTab[0].getPosition();

        for (Grass grass : grassTab) {
            upperRight = upperRight.upperRight(grass.getPosition());
        }
        for (Animal animal : animals.values()) {
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
            return grasses.get(position);
        }
        else return super.objectAt(position);
    }
}
