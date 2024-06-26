package agh.ics.darvin;

import agh.ics.darvin.config.Config;
import agh.ics.darvin.enums.BehaviourType;
import agh.ics.darvin.enums.BoundaryType;
import agh.ics.darvin.enums.ForestType;
import agh.ics.darvin.enums.MutationType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestElementsContainer {
    @Test
    public void addingTest(){
        ElementsContainer container = new ElementsContainer();
        Plant grass = new Plant(new Vector2d(0,0));
        container.addElement(grass);
        Assertions.assertEquals(grass, container.getPlant(new Vector2d(0,0)));
    }

//    @Test
//    public void animalsTest() {
//        ElementsContainer container = new ElementsContainer();
//        IWorldMap map = new RectangularMap(5, 5, BoundaryType.PORTAL, ForestType.TOXIC_FOREST);
//        Vector2d vector2d = new Vector2d(2, 2);
////        Genome.initGenome(4, 0, MutationType.NO_CHANGE, BehaviourType.FULL_PREDESTINATION);
//        Animal animal1 = new Animal(map, vector2d, 10, );
//        Animal animal2 = new Animal(map, vector2d, 10);
//        container.addElement(animal1);
//        container.addElement(animal2);
//
//        IMapElement[] animals = new IMapElement[]{animal1, animal2};
//        Assertions.assertArrayEquals(animals, container.getAnimals(vector2d));
//
//        container.removeAnimal(animal1, animal1.getPosition());
//        Assertions.assertArrayEquals(new IMapElement[]{animal2}, container.getAnimals(vector2d));
//    }
    @Test
    public void animalEmptyTableReturn(){
        ElementsContainer container = new ElementsContainer();
        Assertions.assertArrayEquals(new IWorldMap[0], container.getAnimals(new Vector2d(0,0)));
    }
}
