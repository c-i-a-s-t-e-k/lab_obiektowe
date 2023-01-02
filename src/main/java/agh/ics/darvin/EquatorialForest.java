package agh.ics.darvin;

import java.util.*;

public class EquatorialForest extends AbstractForest {

    private final RectangularMap map;
    private final Set<Vector2d> firstCategoryFields;
    private final Set<Vector2d> secondCategoryFields;


    private Set<Vector2d> findFirstCategoryFields(){
        int numOfFields = this.map.height * this.map.width;

        int numOfFirstCategoryFields = (int)(numOfFields * 0.8);
        int rows = numOfFirstCategoryFields / this.map.width;

        Set<Vector2d> firstCategoryFields = new HashSet<>();

        for (int x = 0; x < this.map.width; x++){
            for (int y = (this.map.height / 2) - (rows / 2); y < (this.map.height / 2) - (rows / 2) + rows; y++){
                firstCategoryFields.add(new Vector2d(x, y));
            }
        }
        for (int i = rows * this.map.width; i < numOfFirstCategoryFields; i++){
            firstCategoryFields.add(new Vector2d(i % this.map.width, i / this.map.width));
        }
        return firstCategoryFields;
    }
    private Set<Vector2d> findSecondCategoryFields(){
        Set<Vector2d> secondCategoryFields = new HashSet<>();

        for (int x = 0; x < this.map.width; x++){
            for(int y = 0; y < this.map.height; y++){
                Vector2d vector = new Vector2d(x,y);
                if(! this.firstCategoryFields.contains(vector)) {
                    secondCategoryFields.add(vector);
                }
            }
        }
        return secondCategoryFields;
    }
    public EquatorialForest(RectangularMap map){
        this.map = map;
        this.firstCategoryFields = findFirstCategoryFields();
        this.secondCategoryFields = findSecondCategoryFields();
    }

    @Override
    public Vector2d placeToGrowSecondCategory() {
        List<Vector2d> places = new ArrayList<>();
        for (Vector2d place : firstCategoryFields)
            if (! this.plantsPositionsInMap.contains(place)) places.add(place);
        if (places.size() > 0) return places.get(random.nextInt() % places.size());
        else return null;    }

    @Override
    public Vector2d placeToGrowFirstCategory() {
        List<Vector2d> places = new ArrayList<>();
        for (Vector2d place : firstCategoryFields)
            if (! this.plantsPositionsInMap.contains(place)) places.add(place);
        if (places.size() > 0) return places.get(random.nextInt() % places.size());
        else return null;
    }
}
