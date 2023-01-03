package agh.ics.darvin.enums;

import agh.ics.darvin.IBoundary;
import agh.ics.darvin.PlanetBoundary;
import agh.ics.darvin.PortalBoundary;
import agh.ics.darvin.RectangularMap;

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
