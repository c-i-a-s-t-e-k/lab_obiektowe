package agh.ics.darvin;

public interface IWorldMap {

    boolean place(IMapElement mapElement);
    Vector2d getFinalPosition(Vector2d position, MapDirection orientation);
    Vector2d getUpperRight();
    Vector2d getLowerLeft();
    boolean canMoveTo(Vector2d position);
    boolean isOccupied(Vector2d position);
    IMapElement[] elementsAt(Vector2d position);
}