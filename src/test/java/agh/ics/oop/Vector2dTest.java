package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Vector2dTest {
    public Vector2d vector1 = new Vector2d(1,4);
    public Vector2d vector2 = new Vector2d(2,5);

    @Test
    public void testEquals(){
        Vector2d vector3 = new Vector2d(1,4);
        Assertions.assertTrue(this.vector1.equals(vector3));
        Assertions.assertTrue(this.vector1.equals((Object) vector3));
        Assertions.assertFalse(this.vector2.equals(this.vector1));
    }

    @Test
    public void testToString(){
        Assertions.assertEquals(this.vector1.toString(), "(1,4)");
    }

    @Test
    public void testPrecedes(){
        Assertions.assertTrue(this.vector1.precedes(this.vector1));
        Assertions.assertTrue(this.vector1.precedes(this.vector2));
        Assertions.assertFalse(this.vector2.precedes(this.vector1));
    }

    @Test
    public void testFollow(){
        Assertions.assertTrue(this.vector1.follows(this.vector1));
        Assertions.assertFalse(this.vector1.follows(this.vector2));
        Assertions.assertTrue(this.vector2.follows(this.vector1));
    }

    @Test
    public void testUpperRight(){
        Vector2d vector3 = new Vector2d(2, 5);
        Vector2d vector4 = new Vector2d(2, 7);
        Assertions.assertEquals(this.vector1.upperRight(this.vector2), vector3);
        Assertions.assertNotEquals(this.vector1.upperRight(this.vector2), vector4);
    }

    @Test
    public void testLowerLeft(){
        Vector2d vector3 = new Vector2d(1, 4);
        Vector2d vector4 = new Vector2d(2, 7);
        Assertions.assertEquals(this.vector1.lowerLeft(this.vector2), vector3);
        Assertions.assertNotEquals(this.vector1.lowerLeft(this.vector2), vector4);
    }

    @Test
    public void testAdd(){
        Vector2d vector3 = new Vector2d(3, 9);
        Vector2d vector4 = new Vector2d(2, 7);
        Assertions.assertEquals(this.vector1.add(this.vector2), vector3);
        Assertions.assertNotEquals(this.vector1.add(this.vector2), vector4);
    }

    @Test
    public void testSubtract(){
        Vector2d vector3 = new Vector2d(-1, -1);
        Vector2d vector4 = new Vector2d(2, 7);
        Assertions.assertEquals(this.vector1.subtract(this.vector2), vector3);
        Assertions.assertNotEquals(this.vector1.subtract(this.vector2), vector4);
    }

    @Test
    public void testOpposite(){
        Vector2d vector3 = new Vector2d(-1, -4);
        Assertions.assertEquals(this.vector1.opposite(), vector3);
        Assertions.assertNotEquals(this.vector1.opposite(), this.vector2);
    }

}
