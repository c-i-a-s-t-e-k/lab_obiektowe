package agh.ics.darvin;

public interface IPlantObserver {
    public void plantAdd(Vector2d position);

    public void plantRemoved(Vector2d position);
}
