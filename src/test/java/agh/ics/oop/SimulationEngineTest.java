package agh.ics.oop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SimulationEngineTest {
    @Test
    public void testRunRectangularMap(){
        IWorldMap map = new RectangularMap(10, 5);
        String[] moves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
        IEngine simulationEngine = new SimulationEngine(OptionsParser.parse(moves), map, positions);
        simulationEngine.run();
        Assertions.assertTrue(map.isOccupied(new Vector2d(3,5)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(2,0)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(2,2)));

        Assertions.assertEquals(((Animal)(map.objectAt(new Vector2d(3,5)))).getOrientation(), MapDirection.NORTH);
        Assertions.assertEquals(((Animal)(map.objectAt(new Vector2d(2,0)))).getOrientation(), MapDirection.SOUTH);
    }

    @Test
    public void testRunGrassField(){
        IWorldMap map = new GrassField(10);
        String[] moves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        Vector2d[] positions = {new Vector2d(2,2), new Vector2d(3,4)};
        IEngine simulationEngine = new SimulationEngine(OptionsParser.parse(moves), map, positions);
        simulationEngine.run();
        Assertions.assertTrue(map.isOccupied(new Vector2d(3,7)));
        Assertions.assertTrue(map.isOccupied(new Vector2d(2,-1)));
        Assertions.assertFalse(map.isOccupied(new Vector2d(2,2)));

        Assertions.assertEquals(((Animal)(map.objectAt(new Vector2d(3,7)))).getOrientation(), MapDirection.NORTH);
        Assertions.assertEquals(((Animal)(map.objectAt(new Vector2d(2,-1)))).getOrientation(), MapDirection.SOUTH);
    }
}
