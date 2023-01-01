package agh.ics.darvin;

public interface IWorldMap {

    boolean place(IMapElement mapElement);
    Vector2d getFinalPosition(Animal animal);
    Vector2d getUpperRight();
    Vector2d getLowerLeft();
    boolean canPutOn(Vector2d position);
    boolean isOccupied(Vector2d position);
    IMapElement[] elementsAt(Vector2d position);
}