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
        Assertions.assertEquals(new Vector2d(0,-6), mapBoundary.getLowerLeft());
        Assertions.assertEquals(new Vector2d(3,5), mapBoundary.getUpperRight());
    }

    @Test
    public void testGetBoundaryGrassField(){
        GrassField map = new GrassField(1);
        String[] moves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f", "r", "r"
        , "f", "f", "f", "f", "f", "f", "f", "f", "f", "f"};
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
        IEngine simulationEngine = new SimulationEngine(OptionsParser.parse(moves), map, positions);
        simulationEngine.run();
        Assertions.assertTrue(map.isOccupied(new Vector2d(8,7)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(-3,-1)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(2,2)));

        Assertions.assertEquals(new Vector2d(-3,-1), map.getLowerLeft());
        Assertions.assertEquals(new Vector2d(8,7), map.getUpperRight());
    }
}
