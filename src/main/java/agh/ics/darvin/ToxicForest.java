package agh.ics.darvin;

import java.util.*;

public class ToxicForest extends AbstractForest implements IDeathsObserver{

    private final Map<Vector2d, ToxicField> fields = new HashMap<>();
    private final Vector2d[] firstCategoryFields;
    private final Vector2d[] secondCategoryFields;
    private final RectangularMap map;
    private final int numOfFirstCategoryFields;

    ToxicForest(RectangularMap map){
        this.map = map;
        this.map.addObserver(this);
        for (int x = 0; x < this.map.width; x++){
            for (int y = 0; y < this.map.height; y++){
                Vector2d vector2d = new Vector2d(x,y);
                fields.put(vector2d, new ToxicField(vector2d));
            }
        }
        int numOfFields = this.map.height * this.map.width;
        this.numOfFirstCategoryFields = (int)(numOfFields * 0.8);

        this.firstCategoryFields = new Vector2d[numOfFirstCategoryFields];
        this.secondCategoryFields = new Vector2d[numOfFields - numOfFirstCategoryFields];
        setFieldsArrays();
    }
    private void setFieldsArrays(){
        setFirstCategoryFields();
        setSecondCategoryFields();
    }
    private ToxicField[] getSortedFields(){
        ToxicField[] fields = this.fields.values().toArray(new ToxicField[0]);
        Arrays.sort(fields);
        return fields;
    }
    private void setFirstCategoryFields(){
        ToxicField[] fields = getSortedFields();
        for (int i = 0; i < numOfFirstCategoryFields; i++){
            this.firstCategoryFields[i] = fields[i].getPosition();
        }
    }
    private void setSecondCategoryFields(){
        ToxicField[] fields = getSortedFields();
        for (int i = numOfFirstCategoryFields; i < fields.length; i++){
            this.secondCategoryFields[i - numOfFirstCategoryFields] = fields[i].getPosition();
        }
    }
    @Override
    public Vector2d placeToGrowFirstCategory() {
        List<Vector2d> places = new ArrayList<>();
        for (Vector2d place : firstCategoryFields)
            if (! this.plantsPositionsInMap.contains(place)) places.add(place);
        if (places.size() > 0) return places.get(random.nextInt() % places.size());
        else return null;
    }

    @Override
    public Vector2d placeToGrowSecondCategory() {
        List<Vector2d> places = new ArrayList<>();
        for (Vector2d place : secondCategoryFields)
            if (! this.plantsPositionsInMap.contains(place)) places.add(place);
        if (places.size() > 0) return places.get(random.nextInt() % places.size());
        else return null;
    }

    @Override
    public void animalDied(Animal animal) {
        fields.get(animal.getPosition()).increaseToxicity();
        setFieldsArrays();
    }
}

