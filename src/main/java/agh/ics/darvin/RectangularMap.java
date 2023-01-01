package agh.ics.darvin;

public class RectangularMap extends AbstractWorldMap{
    public final int width;
    public final int height;
    private static final Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final IBoundary boundary;

    public RectangularMap(int width, int height, BoundaryType boundaryType){
        this.width = width;
        this.height = height;
        this.upperRight = new Vector2d(this.width, this.height);
        this.boundary = boundaryType.getBoundary(this);
    }

    public boolean canPutOn(Vector2d position) {
        return (position.precedes(upperRight) && (position.follows(lowerLeft)));
    }

    public Vector2d getLowerLeft(){
        return lowerLeft;
    }
    public Vector2d getUpperRight(){
        return upperRight;
    }

    @Override
    public Vector2d getFinalPosition(Animal animal) {
        Vector2d finalPosition = super.getFinalPosition(animal);
        if(this.canPutOn(finalPosition)) return finalPosition;
        else return this.boundary.backToBoundary(animal);
    }

}
