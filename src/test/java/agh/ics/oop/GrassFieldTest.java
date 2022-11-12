package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GrassFieldTest {
    @Test
    public void testPlace(){
        IWorldMap map = new GrassField(1);
        Vector2d vector2d = new Vector2d(-1,-1);
        Animal animal = new Animal(map, vector2d);
        Assertions.assertTrue(map.place(animal));
        Assertions.assertSame(animal, map.objectAt(vector2d));
        Assertions.assertFalse(map.place(new Animal(map, vector2d)));

        for(int i = 0;i < 4; i++)
            for (int j= 0; j < 4; j++){
                if(i == 0 && j == 0) continue;
                Assertions.assertTrue(map.place(new Animal(map,new Vector2d(i, j))));
            }
    }
    @Test
    public void testObjectAt(){
        IWorldMap map = new GrassField(1);
        Vector2d vector2d = new Vector2d(-2,-9);
        Animal animal = new Animal(map, vector2d);
        map.place(animal);
        Assertions.assertSame(animal,map.objectAt(vector2d));
        Assertions.assertNull(map.objectAt(new Vector2d(-1,-1)));

        int numberOfFindedGrasses = 0;
        for(int i = 0;i < 4; i++)
            for (int j= 0; j < 4; j++)
                if (map.objectAt(new Vector2d(i,j)) instanceof Grass) numberOfFindedGrasses++;
        Assertions.assertEquals(1, numberOfFindedGrasses);
    }

    @Test
    public void testIsOccupied(){
        IWorldMap map = new GrassField(1);

        boolean flag = false;
        for(int i = 0;i < 4; i++)
            for (int j= 0; j < 4; j++)
                flag = map.isOccupied(new Vector2d(i,j)) || flag;
        Assertions.assertTrue(flag);
        Assertions.assertFalse(map.isOccupied(new Vector2d(-1,-1)));
    }

    @Test
    public void testCanMoveTo(){
        IWorldMap map = new GrassField(1);
        map.place(new Animal(map, new Vector2d(-1,-1)));
        for(int i = 0;i < 4; i++)
            for (int j= 0; j < 4; j++)
                Assertions.assertTrue(map.canMoveTo(new Vector2d(i, j)));
        Assertions.assertFalse(map.canMoveTo(new Vector2d(-1,-1)));
    }
}
