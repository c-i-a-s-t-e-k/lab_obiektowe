package agh.ics.darvin;

public enum ForestType {
    TOXIC_FOREST,
    EQUATORIAL_FOREST;

    public IForest getForest(RectangularMap map){
        IForest forest = null;
        switch (this){
            case TOXIC_FOREST -> {forest = new ToxicForest(map); map.addObserver(forest);}
            case EQUATORIAL_FOREST -> forest = new EquatorialForest(map);
        }
        return forest;
    }
}
