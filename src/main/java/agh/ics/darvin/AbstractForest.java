package agh.ics.darvin;

import java.util.*;

abstract class AbstractForest implements IForest, IPlantObserver{
    protected Iterable<Vector2d> firstCategoryFields;
    protected Iterable<Vector2d> secondCategoryFields;
    protected final Set<Vector2d> plantsPositionsInMap = new HashSet<>();
    protected final Random random = new Random();
    @Override
    public Vector2d placeToGrow() {
        if(random.nextInt() % 100 < 80){
            return placeToGrowFirstCategory();
        }
        else {
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
        for (Vector2d place : firstCategoryFields)
            if (! this.plantsPositionsInMap.contains(place)) places.add(place);
        if (places.size() > 0) return places.get(random.nextInt() % places.size());
        else return null;    }

    public Vector2d placeToGrowFirstCategory() {
        List<Vector2d> places = new ArrayList<>();
        for (Vector2d place : firstCategoryFields)
            if (! this.plantsPositionsInMap.contains(place)) places.add(place);
        if (places.size() > 0) return places.get(random.nextInt() % places.size());
        else return null;
    }
}
