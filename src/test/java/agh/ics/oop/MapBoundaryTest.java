package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MapBoundaryTest {
    @Test
    public void testConstructor(){
        Grass[] grasses = {new Grass(new Vector2d(1,2)),new Grass(new Vector2d(2,5))
                ,new Grass(new Vector2d(0,0)),new Grass(new Vector2d(3,-6))};
        MapBoundary mapBoundary = new MapBoundary(grasses);
        Assertions.assertEquals(new Vector2d(0,-6), mapBoundary.getLoverLeft());
        Assertions.assertEquals(new Vector2d(3,5), mapBoundary.getUpperRight());
    }

    @Test
    public void testPositionChanged(){
        IWorldMap map = new RectangularMap(10,10);
        Animal animal1 = new Animal(map, new Vector2d(3,4));
        Animal animal2 = new Animal(map, new Vector2d(2,1));
        MapBoundary mapBoundary = new MapBoundary();

        animal1.addObserver((IPositionChangeObserver) mapBoundary);
        animal2.addObserver((IPositionChangeObserver) mapBoundary);
        animal1.move(MoveDirection.FORWARD);
        animal2.move(MoveDirection.FORWARD);

        Assertions.assertEquals(new Vector2d(3,5), mapBoundary.getUpperRight());
        Assertions.assertEquals(new Vector2d(2,2), mapBoundary.getLoverLeft());

        animal1.move(MoveDirection.FORWARD);
        animal2.move(MoveDirection.BACKWARD);

        Assertions.assertEquals(new Vector2d(3,6), mapBoundary.getUpperRight());
        Assertions.assertEquals(new Vector2d(2,1), mapBoundary.getLoverLeft());
    }
}
