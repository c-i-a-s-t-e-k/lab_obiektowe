package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public MapDirection getOrientation(){
        return this.orientation;
    }
    public Vector2d getPosition(){
        return this.position;
    }
    public String toString(){
        return "zwierze jest na polu " + this.position + ", zwrócony na " + this.orientation;
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
            this.position = this.position.add(moveVector);
            if ((this.position.x < 0 || 4 < this.position.x) || (this.position.y < 0 || 4 < this.position.y)){
                this.position = this.position.subtract(moveVector);
            }
        }
    }
}
