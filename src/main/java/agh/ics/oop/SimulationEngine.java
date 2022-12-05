package agh.ics.oop;

import agh.ics.oop.gui.App;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.Thread;

public class SimulationEngine implements IEngine, Runnable{
    private MoveDirection[] moves;
    private final IWorldMap map;
    private final List<Animal> animals = new ArrayList<>(0);
    private final int moveDelay;

    public void setMoves(MoveDirection[] moves){
        this.moves = moves;
    }

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this(moves,map,positions,1);
    }

    public SimulationEngine(IWorldMap map, Vector2d[] positions){
        this.map = map;
        this.moveDelay = 300;

        Animal newAnimal = null;
        for (Vector2d position : positions){
            newAnimal =new Animal(map, position);
            if(this.map.place(newAnimal)){
                animals.add(newAnimal);
            }
        }
    }

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions, int moveDelay){
        this.moves = moves;
        this.map = map;
        this.moveDelay = moveDelay;

        Animal newAnimal = null;
        for (Vector2d position : positions){
            newAnimal =new Animal(map, position);
            if(this.map.place(newAnimal)){
                animals.add(newAnimal);
            }
        }
    }

    public void run() {
        for(int i = 0; i < moves.length; i++){
            animals.get(i % animals.size()).move(moves[i]);
            try {
                Thread.sleep(moveDelay);
            }catch (InterruptedException e){
                System.out.println("\n");
                System.out.println(e.getMessage());
                System.out.println("move number " + (i+1) + " not delayed");
                System.out.println("\n");
            }
        }
    }
}
