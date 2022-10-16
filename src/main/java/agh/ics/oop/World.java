package agh.ics.oop;
import java.util.Arrays;
//https://github.com/apohllo/obiektowe-lab/tree/master/lab1
public class World {
    public static void main(String[] args) {
        System.out.println("system wystartowal\n");
        Direction[] enumArgs = stringsToEnums(args);
        run(enumArgs);
        System.out.println("system zakonczyl dzialanie");
    }

    public static void run(Direction[] args) {
        String message;
        for (Direction element : args) {
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

    static Direction[] stringsToEnums(String[] args) {
        Direction[] enumArgs = new Direction[args.length + 1];
        int i = 0;
        for (String dir : args) {
            switch (dir) {
                case "f":
                    enumArgs[i] = Direction.FORWARD;
                    i = i + 1;
                    break;
                case "b":
                    enumArgs[i] = Direction.BACKWARD;
                    i = i + 1;
                    break;
                case "l":
                    enumArgs[i] = Direction.LEFT;
                    i = i + 1;
                    break;
                case "r":
                    enumArgs[i] = Direction.RIGHT;
                    i = i + 1;
                    break;
                default:
                    break;
            }
        }
        return Arrays.copyOfRange(enumArgs, 0, i);
    }
}