package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class SimulationEngine implements IEngine{
    private final MoveDirection[] moves;
    private final IWorldMap map;
    private final List<Animal> animals = new ArrayList<>(0);

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this.moves = moves;
        this.map = map;

        Animal newAnimal = null;
        for (Vector2d position : positions){
            newAnimal =new Animal(map, position);
            if(this.map.place(newAnimal)){
                animals.add(newAnimal);
            }
        }
    }

    public void run(){
        for(int i = 0; i < moves.length; i++){
            animals.get(i % animals.size()).move(moves[i]);
        }
    }
}
