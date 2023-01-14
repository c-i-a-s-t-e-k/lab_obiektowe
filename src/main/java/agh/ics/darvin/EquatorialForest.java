package agh.ics.darvin;

import java.util.*;

public class EquatorialForest extends AbstractForest {
    private final RectangularMap map;

    public EquatorialForest(RectangularMap map) {
        this.map = map;
        findFirstCategoryFields();
        findSecondCategoryFields();
        this.map.addObserver(this);
    }

    private void findFirstCategoryFields() {
        this.firstCategoryFields = new HashSet<>();
        int numOfFields = this.map.height * this.map.width;

        int numOfFirstCategoryFields = (int) (numOfFields * 0.8);
        int rows = numOfFirstCategoryFields / this.map.width;

        for (int x = 0; x < this.map.width; x++) {
            for (int y = (this.map.height / 2) - (rows / 2); y < (this.map.height / 2) - (rows / 2) + rows; y++) {
                ((Set<Vector2d>) this.firstCategoryFields).add(new Vector2d(x, y));
            }
        }
        for (int i = rows * this.map.width; i < numOfFirstCategoryFields; i++) {
            ((Set<Vector2d>) this.firstCategoryFields).add(new Vector2d(i % this.map.width, i / this.map.width));
        }
    }

    private void findSecondCategoryFields() {
        this.secondCategoryFields = new HashSet<>();

        for (int x = 0; x < this.map.width; x++) {
            for (int y = 0; y < this.map.height; y++) {
                Vector2d vector = new Vector2d(x, y);
                if (!((Set<Vector2d>) this.firstCategoryFields).contains(vector)) {
                    ((Set<Vector2d>) this.secondCategoryFields).add(vector);
                }
            }
        }
    }

}
