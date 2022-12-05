package agh.ics.oop;

import javafx.scene.layout.VBox;

import java.util.Map;

public interface IRectanglableMap extends IWorldMap{
    Vector2d getUpperRight();
    Vector2d getLowerLeft();
    Map<Vector2d, VBox> getVBoxAnimals();
}
