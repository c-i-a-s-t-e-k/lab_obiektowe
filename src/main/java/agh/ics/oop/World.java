package agh.ics.oop;
import java.util.Arrays;
//https://github.com/apohllo/obiektowe-lab/tree/master/lab1
public class World {
    public static void main(String[] args) {
//        System.out.println("system wystartowal\n");
//        MoveDirection[] enumArgs = stringsToEnums(args);
//        run(enumArgs);
//        System.out.println("system zakonczyl dzialanie");

//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));

//        MapDirection direction = MapDirection.NORTH;
//        System.out.println(direction.next());
//        System.out.println(direction.next().previous());
//        for (MapDirection el : MapDirection.values()){
//            System.out.println(el.toUnitVector());
//        }

        Animal szynszyl = new Animal();
        System.out.println(szynszyl);
        String[] movesStr = {"right", "f", "backward", "f", "x"};
        for (MoveDirection m : OptionsParser.parse(movesStr)){
            szynszyl.move(m);
        }
        System.out.println(szynszyl);

    }

    public static void run(MoveDirection[] args) {
        String message;
        for (MoveDirection element : args) {
            message = switch (element) {
                case LEFT -> "pojazd skręcił w lewo";
                case RIGHT -> "pojazd skręcił w prawo";
                case BACKWARD -> "pojazd jedzie do tyłu";
                case FORWARD -> "pojazd jedzie do przodu";
            };
            System.out.println(message);
        }
        System.out.println();
    }
}