package agh.ics.darvin;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

abstract class AbstractForest implements IForest, IPlantObserver{
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
    abstract public Vector2d placeToGrowFirstCategory();
    abstract public Vector2d placeToGrowSecondCategory();
}
