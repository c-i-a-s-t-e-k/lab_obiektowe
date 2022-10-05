package agh.ics.oop;
//https://github.com/apohllo/obiektowe-lab/tree/master/lab1
public class World {
    public static void main(String[] args){
        System.out.println("system wystartowal");
        run(args);
        System.out.println("system zakonczyl dzialanie");
    }

    public static void run(String[] args){
        for(String element : args){
            System.out.print(element);
            System.out.print(",");
            if (element.equals("f")){
                System.out.println("zwierzak idzie do przodu");
            }
            if (element.equals("b")){
                System.out.println("zwierzak idzie do tylu");
            }
            if (element.equals("l")){
                System.out.println("zwierzak idzie w lewo");
            }
            if (element.equals("r")){
                System.out.println("zwierzak idzie w prawo");
            }
        }
        System.out.println();
    }
}
