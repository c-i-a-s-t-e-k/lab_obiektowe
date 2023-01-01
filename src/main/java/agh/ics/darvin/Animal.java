package agh.ics.darvin;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractMapElement{
    private MapDirection orientation = MapDirection.NORTH;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();
    private int energy;
    private final Genome genome;


    public Animal(IWorldMap map, Vector2d initialPosition, Genome genome, int energy){
        this.map = map;
        this.position = initialPosition;
        this.energy = energy;
        this.genome = genome;
    }

    public void addObserver(IPositionChangeObserver observer){
        observers.add(observer);
    }
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
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
        this.position = map.getFinalPosition(this.position, this.orientation);
        this.energy--;
        positionChanged(oldPosition);
    }
    private void positionChanged(Vector2d oldPosition){
        for(IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, this.position, this);
        }
    }
    public String getImageName(){
        return "";
    }
}
