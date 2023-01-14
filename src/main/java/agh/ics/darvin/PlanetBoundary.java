package agh.ics.darvin;

public class PlanetBoundary implements IBoundary {
    private final RectangularMap map;

    public PlanetBoundary(RectangularMap map) {
        this.map = map;
    }

    @Override
    public Vector2d backToBoundary(Animal animal) {
        Vector2d position = animal.getPosition();
        int x = 0;
        int y = 0;
        if (position.follows(map.getLowerLeft())) {
            if (position.x < 0) x = map.width;
            if (position.y < 0) y = map.height;
        } else if (position.precedes(map.getUpperRight())) {
            x = position.x % (map.width + 1);
            y = position.y % (map.height + 1);
        }
        return new Vector2d(x, y);
    }
}
