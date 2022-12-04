package agh.ics.oop;

import javafx.util.Pair;

import java.awt.*;
import java.util.*;

public class MapBoundary implements IPositionChangeObserver{
    private final WeighedTreeSet xSet = new WeighedTreeSet();
    private final WeighedTreeSet ySet = new WeighedTreeSet();
    private final Vector2d grassLowerLeft;
    private final Vector2d grassUpperRight;


    public void place(Animal animal){
        animal.addObserver(this);
        xSet.add(animal.getPosition().x);
        ySet.add(animal.getPosition().y);
    }

    public MapBoundary(Grass[] grasses){
        if (grasses.length > 0){
        Vector2d grassLoverLeft = grasses[0].getPosition();
        Vector2d grassUpperRight = grasses[0].getPosition();
        for(Grass grass : grasses){
            grassLoverLeft = grassLoverLeft.lowerLeft(grass.getPosition());
            grassUpperRight = grassUpperRight.upperRight(grass.getPosition());
        }
        this.grassLowerLeft = grassLoverLeft;
        this.grassUpperRight = grassUpperRight;
        }else {
            this.grassUpperRight = null;
            this.grassLowerLeft = null;
        }
    }
    MapBoundary(){
        this.grassUpperRight = null;
        this.grassLowerLeft = null;
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        xSet.remove(oldPosition.x);
        xSet.add(newPosition.x);

        ySet.remove(oldPosition.y);
        ySet.add(newPosition.y);
    }

    public Vector2d getLowerLeft(){
        if (xSet.isEmpty() || ySet.isEmpty())
            return this.grassLowerLeft;
        if (this.grassLowerLeft == null) return new Vector2d(xSet.first(), ySet.first());
        return this.grassLowerLeft.lowerLeft(new Vector2d(xSet.first(), ySet.first()));
    }

    public Vector2d getUpperRight(){
        if (xSet.isEmpty() || ySet.isEmpty())
            return this.grassUpperRight;
        if (this.grassUpperRight == null) return new Vector2d(xSet.last(), ySet.last());
        return this.grassUpperRight.upperRight(new Vector2d(xSet.last(), ySet.last()));
    }
}