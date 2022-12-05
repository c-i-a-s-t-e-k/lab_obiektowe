package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;


//https://github.com/apohllo/obiektowe-lab/tree/master/lab1
public class World {
    public static void main(String[] args) {
        try {
            Application.launch(App.class, args);
        }
        catch (IllegalArgumentException ex){
            System.out.println("\n");
            System.out.println(ex.getMessage());
            System.out.println("programme end task becaous of exception");
            System.out.println("\n");
        }
    }

}