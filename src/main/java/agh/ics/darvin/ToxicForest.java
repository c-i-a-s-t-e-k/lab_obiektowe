package agh.ics.darvin;

import java.util.*;

public class ToxicForest extends AbstractForest implements IDeathsObserver{

    private final Map<Vector2d, ToxicField> fields = new HashMap<>();
    private final RectangularMap map;
    private final int numOfFirstCategoryFields;

    public ToxicForest(RectangularMap map){
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

        this.firstCategoryFields = new ArrayList<>();
        this.secondCategoryFields = new ArrayList<>();
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
            ((List<Vector2d>) this.firstCategoryFields).add(fields[i].getPosition());
        }
    }
    private void setSecondCategoryFields(){
        ToxicField[] fields = getSortedFields();
        for (int i = numOfFirstCategoryFields; i < fields.length; i++){
            ((List<Vector2d>) this.firstCategoryFields).add(fields[i].getPosition());
        }
    }

    @Override
    public void animalDied(Animal animal) {
        fields.get(animal.getPosition()).increaseToxicity();
        setFieldsArrays();
    }
}

