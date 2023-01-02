package agh.ics.darvin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;

public class TestElementsContainer {
    @Test
    public void addingTest(){
        ElementsContainer container = new ElementsContainer();
        Plant grass = new Plant(new Vector2d(0,0));
        container.addElement(grass);
        Assertions.assertEquals(grass, container.getPlant(new Vector2d(0,0)));
    }

    @Test
    public void animalsTest() {
        ElementsContainer container = new ElementsContainer();
        IWorldMap map = new RectangularMap(5, 5, BoundaryType.PORTAL);
        Vector2d vector2d = new Vector2d(2, 2);
        Genome.initGenome(4, 0, MutationType.NO_CHANGE);
        Animal animal1 = new Animal(map, vector2d, new Genome(), 10);
        Animal animal2 = new Animal(map, vector2d, new Genome(), 10);
        container.addElement(animal1);
        container.addElement(animal2);

        IMapElement[] animals = new IMapElement[]{animal1, animal2};
        Assertions.assertArrayEquals(animals, container.getAnimals(vector2d));

        container.removeAnimal(animal1, animal1.getPosition());
        Assertions.assertArrayEquals(new IMapElement[]{animal2}, container.getAnimals(vector2d));
    }
    @Test
    public void animalEmptyTableReturn(){
        ElementsContainer container = new ElementsContainer();
        Assertions.assertArrayEquals(new IWorldMap[0], container.getAnimals(new Vector2d(0,0)));
    }
}
