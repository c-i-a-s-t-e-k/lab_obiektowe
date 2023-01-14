package agh.ics.darvin;  // dużo plików w tym pakiecie -> przydałoby się podzielić

import java.util.*;

abstract class AbstractForest implements IForest, IPlantObserver {
    protected Iterable<Vector2d> firstCategoryFields;
    protected Iterable<Vector2d> secondCategoryFields;
    protected final Set<Vector2d> plantsPositionsInMap = new HashSet<>();
    protected static final Random random = new Random();

    @Override
    public Vector2d placeToGrow() {
        if (random.nextInt() % 100 < 80) {
            return placeToGrowFirstCategory();
        } else {
            return placeToGrowSecondCategory();
        }
    }

    @Override
    public void plantRemoved(Vector2d position) {
        this.plantsPositionsInMap.remove(position);
    }

    @Override
    public void plantAdd(Vector2d position) {
        this.plantsPositionsInMap.add(position);
    }

    public Vector2d placeToGrowSecondCategory() {
        List<Vector2d> places = new ArrayList<>();
        for (Vector2d place : firstCategoryFields)  // tak to jest, jak się używa metody Copy'ego-Paste'a
            if (!this.plantsPositionsInMap.contains(place)) places.add(place);
        if (places.size() > 0) return places.get(Math.abs(random.nextInt()) % places.size());
        else return null;
    }

    public Vector2d placeToGrowFirstCategory() {
        List<Vector2d> places = new ArrayList<>();
        for (Vector2d place : firstCategoryFields)
            if (!this.plantsPositionsInMap.contains(place)) places.add(place);
        if (places.size() > 0) return places.get(Math.abs(random.nextInt()) % places.size());
        else return null;
    }
}
