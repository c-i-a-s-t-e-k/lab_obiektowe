package agh.ics.oop;
//https://github.com/apohllo/obiektowe-lab/tree/master/lab1
public class World {
    public static void main(String[] args) {
        try {
            String[] stringMoves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
            MoveDirection[] directions = new OptionsParser().parse(stringMoves);
            IWorldMap map = new GrassField(1);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4), new Vector2d(3,4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            System.out.println(map);
            engine.run();
            System.out.println(map);
        }
        catch (IllegalArgumentException ex){
            System.out.println("\n");
            System.out.println(ex.getMessage());
            System.out.println("programme end task becaous of exception");
            System.out.println("\n");
        }
    }
}