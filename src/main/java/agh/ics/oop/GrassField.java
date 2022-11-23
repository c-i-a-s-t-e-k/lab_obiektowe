package agh.ics.oop;

import java.lang.Math;
import java.util.HashMap;
import java.util.Map;

public class GrassField extends AbstractWorldMap{
    private static final Vector2d lowerLeftGrassPosition = new Vector2d(0,0);
    private final Map<Vector2d,Grass> grasses = new HashMap<>();
    private final Vector2d upperRightGrassPosition;
    private final MapBoundary mapBoundary;


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
        Grass[] grassesTab = grasses.values().toArray(new Grass[0]);
        mapBoundary = new MapBoundary(grassesTab);
    }

    @Override
    public Vector2d getLowerLeft(){
        return  mapBoundary.getLoverLeft();
    }

    @Override
    public Vector2d getUpperRight(){
        return mapBoundary.getUpperRight();
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

    @Override
    public boolean place(Animal animal){
        if (super.place(animal)) {
            animal.addObserver(this.mapBoundary);
            return true;
        }
        else return false;
        }
}
