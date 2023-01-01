package agh.ics.darvin;

enum MapDirection {
    NORTH (new Vector2d(0,1), "N"),
    NORTHEAST (new Vector2d(1,1), "NE"),
    EAST (new Vector2d(1,0), "E"),
    SOUTHEAST (new Vector2d(1, -1), "SE"),
    SOUTH (new Vector2d(0,-1), "S"),
    SOUTHWEST (new Vector2d(-1,-1), "SW"),
    WEST (new Vector2d(-1,0), "W"),
    NORTHWEST (new Vector2d(-1, 1), "NW");

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
            case NORTH: return NORTHEAST;
            case SOUTH: return SOUTHWEST;
            case EAST: return SOUTHEAST;
            case WEST: return NORTHWEST;
            case NORTHEAST: return EAST;
            case NORTHWEST: return NORTH;
            case SOUTHEAST: return SOUTH;
            case SOUTHWEST: return WEST;
        }
        return null;
    }

    public MapDirection previous(){
        switch (this){
            case NORTH: return NORTHWEST;
            case SOUTH: return SOUTHEAST;
            case EAST: return NORTHEAST;
            case WEST: return SOUTHWEST;
            case NORTHEAST: return NORTH;
            case NORTHWEST: return WEST;
            case SOUTHEAST: return EAST;
            case SOUTHWEST: return SOUTH;
        }
        return null;
    }

    public Vector2d toUnitVector(){
        return unitVector;
    }
}
