package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class AnimalTest {
    @Test
    public void test1Move(){
        Animal animal1 = new Animal();
        Assertions.assertEquals(animal1.getOrientation(), MapDirection.NORTH);
        animal1.move(MoveDirection.RIGHT);
        Assertions.assertEquals(animal1.getOrientation(), MapDirection.EAST);
        animal1.move(MoveDirection.RIGHT);
        Assertions.assertEquals(animal1.getOrientation(), MapDirection.SOUTH);
        animal1.move(MoveDirection.RIGHT);
        Assertions.assertEquals(animal1.getOrientation(), MapDirection.WEST);
        animal1.move(MoveDirection.RIGHT);
        Assertions.assertEquals(animal1.getOrientation(), MapDirection.NORTH);

        animal1.move(MoveDirection.LEFT);
        Assertions.assertEquals(animal1.getOrientation(), MapDirection.WEST);
        animal1.move(MoveDirection.LEFT);
        Assertions.assertEquals(animal1.getOrientation(), MapDirection.SOUTH);
        animal1.move(MoveDirection.LEFT);
        Assertions.assertEquals(animal1.getOrientation(), MapDirection.EAST);
        animal1.move(MoveDirection.LEFT);
        Assertions.assertEquals(animal1.getOrientation(), MapDirection.NORTH);
        animal1.move(MoveDirection.LEFT);
    }
    @Test
    public void test2Move(){
        Animal animal1 = new Animal();
        Vector2d vector0 = new Vector2d(2, 2);
        Assertions.assertEquals(animal1.getPosition(), vector0);

        animal1.move(MoveDirection.FORWARD);
        Assertions.assertEquals(animal1.getPosition(), new Vector2d(2, 3));
        animal1.move(MoveDirection.BACKWARD);
        Assertions.assertEquals(animal1.getPosition(), vector0);

        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.FORWARD);
        Assertions.assertEquals(animal1.getPosition(), new Vector2d(3, 2));
        animal1.move(MoveDirection.BACKWARD);
        Assertions.assertEquals(animal1.getPosition(), vector0);

        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.FORWARD);
        Assertions.assertEquals(animal1.getPosition(), new Vector2d(2, 1));
        animal1.move(MoveDirection.BACKWARD);
        Assertions.assertEquals(animal1.getPosition(), vector0);

        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.FORWARD);
        Assertions.assertEquals(animal1.getPosition(), new Vector2d(1, 2));
        animal1.move(MoveDirection.BACKWARD);
        Assertions.assertEquals(animal1.getPosition(), vector0);
    }

    @Test
    public void test3Move(){
        Animal animal1 = new Animal();

        for(int i = 0; i < 5; i++){
            animal1.move(MoveDirection.FORWARD);
        }
        Assertions.assertEquals(animal1.getPosition(), new Vector2d(2, 4));

        animal1.move(MoveDirection.RIGHT);
        for(int i = 0; i < 5; i++){
            animal1.move(MoveDirection.FORWARD);
        }
        Assertions.assertEquals(animal1.getPosition(), new Vector2d(4, 4));

        animal1.move(MoveDirection.RIGHT);
        for(int i = 0; i < 5; i++){
            animal1.move(MoveDirection.FORWARD);
        }
        Assertions.assertEquals(animal1.getPosition(), new Vector2d(4, 0));

        animal1.move(MoveDirection.RIGHT);
        for(int i = 0; i < 5; i++){
            animal1.move(MoveDirection.FORWARD);
        }
        Assertions.assertEquals(animal1.getPosition(), new Vector2d(0, 0));
    }

}
