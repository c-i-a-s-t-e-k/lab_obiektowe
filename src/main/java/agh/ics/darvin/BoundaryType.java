package agh.ics.darvin;

public enum BoundaryType {
    PLANET,
    PORTAL;

    public IBoundary getBoundary(RectangularMap map){
        return switch (this){
            case PLANET -> new PlanetBoundary(map);
            case PORTAL -> new PortalBoundary(map);
        };
    }
}
