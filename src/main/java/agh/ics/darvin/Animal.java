package agh.ics.darvin;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractMapElement{
    private AnimalManager manager;
    private static int startEnergy;
    private static int reproductionCost;
    private MapDirection orientation = MapDirection.NORTH;
    private final IWorldMap map;
    private int energy = startEnergy;
    private final Genome genome;

    public static void initAnimal(int startEnergy, int reproductionCost){
        if (startEnergy > 0)
            Animal.startEnergy = startEnergy;
        else
            throw new IllegalArgumentException("energy must be higher than 0");
        if (reproductionCost >= 0)
            Animal.reproductionCost = reproductionCost;
        else throw new IllegalArgumentException("reproduction cost must be higher than -1");
    }

    public Animal(IWorldMap map, Vector2d initialPosition, Genome genome){
        this(map,initialPosition,genome,Animal.startEnergy);
    }
    public Animal(IWorldMap map, Vector2d initialPosition, Genome genome, int energy){
        this.map = map;
        this.position = initialPosition;
        this.energy = energy;
        this.genome = genome;
    }


    public MapDirection getOrientation(){
        return this.orientation;
    }
    public String toString(){
        return this.orientation.toString();
    }

    public Boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(){
        int rotations = genome.getGene();
        for (int i = 0; i < rotations; i++){
            this.orientation = this.orientation.next();
        }
        Vector2d oldPosition = this.position;
        this.position = map.getFinalPosition(this);
        this.energy--;
        positionChanged(oldPosition);
    }

    private void positionChanged(Vector2d oldPosition){
        this.manager.animalChangedPosition(oldPosition, this);
    }
    public void decreaseEnergy(){
        this.energy = this.energy - Animal.reproductionCost;
    }
    public String getImageName(){
        return "";
    }
    public void setManager(AnimalManager manager){
        this.manager = manager;
    }
}
