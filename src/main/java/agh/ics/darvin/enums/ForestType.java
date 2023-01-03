package agh.ics.darvin.enums;

import agh.ics.darvin.EquatorialForest;
import agh.ics.darvin.IForest;
import agh.ics.darvin.RectangularMap;
import agh.ics.darvin.ToxicForest;

public enum ForestType {
    TOXIC_FOREST,
    EQUATORIAL_FOREST;

    static public ForestType fromString(String name){
        for (var e : ForestType.values()) {
            if (name == e.toString())
                return e;
        }
        return ForestType.values()[0]; // On error return default value
    }

    public IForest getForest(RectangularMap map){
        IForest forest = null;
        switch (this){
            case TOXIC_FOREST -> {forest = new ToxicForest(map); map.addObserver(forest);}
            case EQUATORIAL_FOREST -> forest = new EquatorialForest(map);
        }
        return forest;
    }
}
