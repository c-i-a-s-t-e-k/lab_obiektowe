package agh.ics.oop;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class SimulationEngine implements IEngine{
    private final List<MoveDirection> moves;
    private final IWorldMap map;
    private final List<Animal> animals = new ArrayList<>(0);

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this.moves = Arrays.asList(moves);
        this.map = map;

        for (Vector2d position : positions){
            Animal newAnimal =new Animal(map, position);
            if(this.map.place(newAnimal)){
                animals.add(newAnimal);
            }
        }
    }

    public void run(){
        for(int i = 0; i < moves.size(); i++){
            animals.get(i % animals.size()).move(moves.get(i));
        }
    }
}
