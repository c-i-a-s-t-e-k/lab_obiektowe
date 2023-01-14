package agh.ics.darvin;

enum MapDirection {
    NORTH(new Vector2d(0, 1), "N"),
    NORTHEAST(new Vector2d(1, 1), "NE"),
    EAST(new Vector2d(1, 0), "E"),
    SOUTHEAST(new Vector2d(1, -1), "SE"),
    SOUTH(new Vector2d(0, -1), "S"),
    SOUTHWEST(new Vector2d(-1, -1), "SW"),
    WEST(new Vector2d(-1, 0), "W"),
    NORTHWEST(new Vector2d(-1, 1), "NW");

    private final Vector2d unitVector;
    private final String directionName;

    private MapDirection(Vector2d unitVector, String directionName) {
        this.directionName = directionName;
        this.unitVector = unitVector;
    }

    public String toString() {
        return directionName;
    }

    public MapDirection next() {
        return switch (this) {
            case NORTH -> NORTHEAST;
            case SOUTH -> SOUTHWEST;
            case EAST -> SOUTHEAST;
            case WEST -> NORTHWEST;
            case NORTHEAST -> EAST;
            case NORTHWEST -> NORTH;
            case SOUTHEAST -> SOUTH;
            case SOUTHWEST -> WEST;
        };
    }

    public MapDirection previous() {
        return switch (this) {
            case NORTH -> NORTHWEST;
            case SOUTH -> SOUTHEAST;
            case EAST -> NORTHEAST;
            case WEST -> SOUTHWEST;
            case NORTHEAST -> NORTH;
            case NORTHWEST -> WEST;
            case SOUTHEAST -> EAST;
            case SOUTHWEST -> SOUTH;
        };
    }

    public Vector2d toUnitVector() {
        return unitVector;
    }
}
