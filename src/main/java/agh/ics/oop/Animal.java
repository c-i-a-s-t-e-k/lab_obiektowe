package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public String toString(){
        return "zwierze jest na polu " + this.position + ", zwrócony na " + this.orientation;
    }

    public Boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    private void move(int a){
        Vector2d moveVec = switch (this.orientation){
            case NORTH -> new Vector2d(0,a);
            case SOUTH -> new Vector2d(0, -a);
            case EAST -> new Vector2d(a, 0);
            case WEST -> new Vector2d(-a, 0);
        };

        this.position.add(moveVec);
        if(!((0 <= this.position.x && this.position.x <= 4) || (0 <= this.position.y && this.position.y <= 4))){
            System.out.println("non");
            this.position.subtract(moveVec);
        }
    }

    public void move(MoveDirection direction){
        switch (direction){
            case LEFT:
                this.orientation = this.orientation.previous();
                break;
            case RIGHT:
                this.orientation = this.orientation.next();
                break;
            case FORWARD:
                move(1);
                break;
            case BACKWARD:
                move(-1);
                break;
        }
//        this.position.add(new Vector2d(1,1));
    }
}
