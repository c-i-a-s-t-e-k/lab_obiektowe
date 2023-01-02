package agh.ics.darvin;

public enum ForestType {
    TOXIC_FOREST,
    EQUATORIAL_FOREST;

    public IForest getForest(RectangularMap map){
        return switch (this){
            case TOXIC_FOREST -> new ToxicForest(map);
            case EQUATORIAL_FOREST -> new EquatorialForest(map);
        };
    }
}
