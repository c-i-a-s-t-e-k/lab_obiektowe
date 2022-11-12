package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap{
    public final int width;
    public final int height;
    private static final Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.upperRight = new Vector2d(this.width, this.height);
    }


    public boolean canMoveTo(Vector2d position) {
        return (position.precedes(upperRight) && (position.follows(lowerLeft)) && (!isOccupied(position)));
    }

    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
