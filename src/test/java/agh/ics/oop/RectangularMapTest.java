package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangularMapTest {

    @Test
    public void testPlaceAndIsOccupied(){
        IWorldMap map = new RectangularMap(10, 2);

        Assertions.assertTrue(map.place(new Animal(map,new Vector2d(5, 1))));
        Assertions.assertTrue(map.isOccupied(new Vector2d(5, 1)));

        Assertions.assertFalse(map.isOccupied(new Vector2d(0,0)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(0,11)));
    }

    @Test
    public void testObjectAt(){
        IWorldMap map = new RectangularMap(10, 10);
        Animal animal = new Animal(map);

        map.place(animal);

        Assertions.assertTrue(animal == map.objectAt(new Vector2d(2,2)));
        Assertions.assertEquals(null, map.objectAt(new Vector2d(2,4)));
    }
    @Test
    public void testPlace(){
        IWorldMap map = new RectangularMap(2,2);
        Vector2d vector2d = new Vector2d(0,0);
        Animal animal = new Animal(map, vector2d);
        Assertions.assertTrue(map.place(animal));
        Assertions.assertSame(animal, map.objectAt(vector2d));
        Assertions.assertFalse(map.place(new Animal(map, vector2d)));
    }
    @Test
    public void testAnimalsOnSamePlace(){
        IWorldMap map = new RectangularMap(4,4);
        map.place(new Animal(map, new Vector2d(2,2)));
        Assertions.assertFalse(map.place(new Animal(map, new Vector2d(2,2))));

        map.place(new Animal(map, new Vector2d(2,1)));
        ((Animal) map.objectAt(new Vector2d(2,1))).move(MoveDirection.FORWARD);

        Assertions.assertTrue(((Animal) map.objectAt(new Vector2d(2,1))).isAt(new Vector2d(2,1)));
    }
}
