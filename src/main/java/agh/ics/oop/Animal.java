package agh.ics.oop;
public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);
    private final IWorldMap map;

//    use this()
    public Animal(){
        this.map = new RectangularMap(4,4);
    }
    public Animal(IWorldMap map){
        this.map = map;
    }
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        this.position = initialPosition;
    }

    public MapDirection getOrientation(){
        return this.orientation;
    }
    public Vector2d getPosition(){
        return this.position;
    }
    public String toString(){
        return this.orientation.toString();
    }

    public Boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction){

        Vector2d moveVector = null;

        switch (direction){
            case LEFT:
                this.orientation = this.orientation.previous();
                break;
            case RIGHT:
                this.orientation = this.orientation.next();
                break;
            case FORWARD:
                moveVector = this.orientation.toUnitVector();
                break;
            case BACKWARD:
                moveVector = this.orientation.toUnitVector().opposite();
                break;
        }
        if (moveVector != null){
            if (map.canMoveTo(this.position.add(moveVector))){
                this.position = this.position.add(moveVector);
            }
        }
    }
}
