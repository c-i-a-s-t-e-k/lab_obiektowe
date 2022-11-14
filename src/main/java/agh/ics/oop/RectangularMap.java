package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap{
    public final int width;
    public final int height;
    private static final Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        this.upperRight = new Vector2d(this.width, this.height);
    }

    public boolean canMoveTo(Vector2d position) {
        return (position.precedes(upperRight) && (position.follows(lowerLeft)) && super.canMoveTo(position));
    }

    public Vector2d getLowerLeft(){
        return lowerLeft;
    }
    public Vector2d getUpperRight(){
        return upperRight;
    }
}
