package agh.ics.darvin;

import javafx.scene.shape.Shape;

public interface IMapElement {
    String getImageName();
    Vector2d getPosition();
    Shape get_representation(CanSetAnimal setAnimal);

}
