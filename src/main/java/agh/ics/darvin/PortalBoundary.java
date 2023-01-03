package agh.ics.darvin;

public class PortalBoundary implements IBoundary{
    private final RectangularMap map;
    public PortalBoundary(RectangularMap map){
        this.map = map;
    }
    public Vector2d backToBoundary(Animal animal){
        animal.decreaseEnergy();
        return Vector2d.randomVectorInRectangle(map.getLowerLeft(), map.getUpperRight());
    }
}
