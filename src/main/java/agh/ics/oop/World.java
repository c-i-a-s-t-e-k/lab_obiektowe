package agh.ics.oop;
//https://github.com/apohllo/obiektowe-lab/tree/master/lab1
public class World {
    public static void main(String[] args){
        System.out.println("system wystartowal\n");
        Direction[] enumArgs = new Direction[args.length];
        for(int i = 0;i < args.length; i++) {
            enumArgs[i] = switch (args[i]){
                case "f" -> Direction.Forward;
                case "b" -> Direction.Backward;
                case "l" -> Direction.Left;
                case "r" -> Direction.Right;
                default -> Direction.Nothing;
            };
        }
        run(enumArgs);
        System.out.println("system zakonczyl dzialanie");
    }

    public static void run(Direction[] args){
        String message;
        for(Direction element : args){
            message = switch (element){
                case Left -> "pojazd skręcił w lewo";
                case Right -> "pojazd skręcił w prawo";
                case Backward -> "pojazd jedzie do tyłu";
                case Forward -> "pojazd jedzie do przodu";
                default -> "nieznany kierunek";
            };
            System.out.println(message);
        }
        System.out.println();
    }
}
