package agh.ics.oop;
import java.util.Arrays;
//https://github.com/apohllo/obiektowe-lab/tree/master/lab1
public class World {
    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = (IEngine) new SimulationEngine(directions, map, positions);
        System.out.println(map);
        engine.run();
        System.out.println(map);
    }
}