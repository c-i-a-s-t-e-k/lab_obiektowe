package agh.ics.oop;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{

    private final Vector2d grassLoverLeft;
    private final Vector2d grassUpperRight;

    public MapBoundary(Grass[] grasses){
        if (grasses.length > 0){
        Vector2d grassLoverLeft = grasses[0].getPosition();
        Vector2d grassUpperRight = grasses[0].getPosition();
        for(Grass grass : grasses){

            grassLoverLeft = grassLoverLeft.lowerLeft(grass.getPosition());
            grassUpperRight = grassUpperRight.upperRight(grass.getPosition());
        }
        this.grassLoverLeft = grassLoverLeft;
        this.grassUpperRight = grassUpperRight;
        }else {
            this.grassUpperRight = null;
            this.grassLoverLeft = null;
        }
    }
    public MapBoundary(){
        this.grassUpperRight = null;
        this.grassLoverLeft = null;
    }

    private final TreeSet<Integer> xSet = new TreeSet<>();
    private final TreeSet<Integer> ySet = new TreeSet<>();
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        xSet.remove(oldPosition.x);
        xSet.add(newPosition.x);

        ySet.remove(oldPosition.y);
        ySet.add(newPosition.y);
    }

    public Vector2d getLoverLeft(){
        if (xSet.isEmpty()) return grassLoverLeft;
        if (grassLoverLeft == null) return new Vector2d(xSet.first(), ySet.first());
        return grassLoverLeft.lowerLeft(new Vector2d(xSet.first(), ySet.first()));
    }

    public Vector2d getUpperRight(){
        if (ySet.isEmpty()) return grassUpperRight;
        if (grassUpperRight == null) return new Vector2d(xSet.last(), ySet.last());
        return grassUpperRight.upperRight(new Vector2d(xSet.last(), ySet.last()));
    }
}
