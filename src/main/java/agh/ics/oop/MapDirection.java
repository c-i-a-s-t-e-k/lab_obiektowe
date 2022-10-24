package agh.ics.oop;

import javax.lang.model.type.NullType;

enum MapDirection {
    NORTH (new Vector2d(0,1), "N"),
    SOUTH (new Vector2d(0,-1), "S"),
    WEST (new Vector2d(-1,0), "W"),
    EAST (new Vector2d(1,0), "E");

    private final Vector2d unitVector;
    private final String directionName;

    private MapDirection(Vector2d unitVector, String directionName){
        this.directionName = directionName;
        this.unitVector = unitVector;
    }

    public String toString(){
        return directionName;
    }

    public MapDirection next(){
        switch (this){
            case NORTH: return EAST;
            case SOUTH: return WEST;
            case EAST: return SOUTH;
            case WEST: return NORTH;
        }
        return null;
    }

    public MapDirection previous(){
        switch (this){
            case NORTH: return WEST;
            case SOUTH: return EAST;
            case EAST: return NORTH;
            case WEST: return SOUTH;
        }
        return null;
    }

    public Vector2d toUnitVector(){
        return unitVector;
    }
}
