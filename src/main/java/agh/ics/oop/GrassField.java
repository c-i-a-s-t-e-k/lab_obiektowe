package agh.ics.oop;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

public class GrassField extends AbstractWorldMap{
    private static final Vector2d lowerLeftGrassPosition = new Vector2d(0,0);
    private final List<Grass> grassList = new ArrayList<>();
    private int grassAmount;
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

    public void eatGrass(Grass grassToEat){
        if(grassList.contains(grassToEat)) {
            grassList.remove(grassToEat);
            placeGrass(1);
        }
    }

    public GrassField(int grassAmount){
        this.grassAmount = grassAmount;
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
    public Object objectAt(Vector2d position) {
        if (super.objectAt(position) == null) {
            for (Grass grass : grassList) {
                if (position.equals(grass.getPosition())) return (Object) grass;
            }
            return null;
        }
        else return super.objectAt(position);
    }

    @Override
    public boolean place(Animal animal){
        if((upperRightGrassPosition.x + 1) * (upperRightGrassPosition.y + 1) < grassAmount + animalList.size())
            return false;
        Object somethink = this.objectAt(animal.getPosition());
        if(super.place(animal)){
            if (somethink instanceof Grass)
                eatGrass((Grass) somethink);
            return true;
        }
        return false;
    }
}
