package agh.ics.darvin;

public interface IPositionChangeObserver {
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Animal animal);
}
