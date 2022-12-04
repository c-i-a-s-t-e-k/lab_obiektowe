package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement{
    private MapDirection orientation = MapDirection.NORTH;
    private final IWorldMap map;
    private final List<IPositionChangeObserver> observers = new ArrayList<>();
    protected Vector2d position;

    public Vector2d getPosition(){
        return position;
    }

    public Animal(){
        this(new RectangularMap(4,4), new Vector2d(2,2));
    }
//    public Animal(IWorldMap map){
//        this(map, new Vector2d(2,2));
//    }
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
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

    public void move(MoveDirection direction){

        Vector2d moveVector = null;

        switch (direction) {
            case LEFT -> this.orientation = this.orientation.previous();
            case RIGHT -> this.orientation = this.orientation.next();
            case FORWARD -> moveVector = this.orientation.toUnitVector();
            case BACKWARD -> moveVector = this.orientation.toUnitVector().opposite();
        }
        if (moveVector != null){
            Vector2d oldPosition = this.position;
            Vector2d newPosition = this.position.add(moveVector);
            if (map.canMoveTo(newPosition)){
                this.position = newPosition;
                this.positionChanged(oldPosition);
            }
        }
    }
    private void positionChanged(Vector2d oldPosition){
        for(IPositionChangeObserver observer : observers){
            observer.positionChanged(oldPosition, this.position);
        }
    }
    public String getImageName(){
        System.out.println("zwierze" + this.orientation.toString() + ".jpg");
        return "zwierze" + this.orientation.toString() + ".jpg";
    }
}
