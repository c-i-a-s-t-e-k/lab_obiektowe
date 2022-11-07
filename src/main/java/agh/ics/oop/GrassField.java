package agh.ics.oop;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrassField implements IWorldMap{
    private Vector2d lowerLeft;
    private Vector2d upperRight;
    private static final Vector2d lowerLeftGrassPosition = new Vector2d(0,0);
    private final List<Grass> grassList = new ArrayList<>();
    private final List<Animal> animalList = new ArrayList<>();
    private final Vector2d upperRightGrassPosition;

    public GrassField(int grassAmount){
        upperRightGrassPosition = new Vector2d((int) Math.sqrt(grassAmount),(int) Math.sqrt(grassAmount));
        int i = 0;
        while (i < grassAmount){
            Vector2d newPlace = Vector2d.randomVectorInRectangle(lowerLeftGrassPosition, upperRightGrassPosition);

            if (! isOccupied(newPlace)){
                grassList.add(new Grass(newPlace));
                i ++;
            }
        }
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public boolean place(Animal animal) {
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }
}
