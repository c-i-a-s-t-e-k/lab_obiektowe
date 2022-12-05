package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.util.Map;

public class MapGridManager implements IPositionChangeObserver{
    private final Map<Vector2d, VBox> vBoxMapAnimals;
    private final Map<Vector2d, VBox> vBoxMapGrasses;
    private final GridPane gridPane;
    private final IRectanglableMap map;
    private int height;
    private int width;
    private final int sizeOfCell = 40;
    private Vector2d translationVector;

    public MapGridManager(GridPane grid, IWorldMap map){
        this.gridPane = grid;
        this.map = (IRectanglableMap)map;

        this.vBoxMapAnimals = this.map.getVBoxAnimals();
        if(map instanceof GrassField)
            this.vBoxMapGrasses = ((GrassField)this.map).getVBoxGrasses();
        else this.vBoxMapGrasses = null;

        this.translationVector = new Vector2d(-this.map.getLowerLeft().x, this.map.getUpperRight().y);
        this.height = this.map.getUpperRight().y - this.map.getLowerLeft().y;
        this.width = this.map.getUpperRight().x - this.map.getLowerLeft().x;

        this.gridPane.setGridLinesVisible(true);
        this.gridPane.setAlignment(Pos.CENTER);

        fillGrid();
        drawAxis();
    }

    public GridPane getGrid(){
        return gridPane;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getCellSize() {
        return sizeOfCell;
    }

    public void place(Animal animal){
        if(vBoxMapAnimals.containsKey(animal.getPosition()))
            throw new IllegalArgumentException("Animal at position:" + animal.getPosition() + "already in grid");
        else {
            animal.addObserver(this);
            addVBoxToGrid(animal.getPosition());
        }

    }
    private void fillGrid(){
        for (Vector2d key : vBoxMapAnimals.keySet()){
            Vector2d positionOnGrid = new Vector2d(key.x,-key.y).add(translationVector);
            gridPane.add(vBoxMapAnimals.get(key), positionOnGrid.x + 1, positionOnGrid.y + 1, 1,1);
        }
        if (!(map instanceof GrassField)) return;

        for (Vector2d key : vBoxMapGrasses.keySet()){
            if (! vBoxMapAnimals.containsKey(key)){
                Vector2d positionOnGrid = new Vector2d(key.x,-key.y).add(translationVector);
                gridPane.add(vBoxMapGrasses.get(key), positionOnGrid.x + 1, positionOnGrid.y + 1, 1,1);
            }
        }
    }
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Platform.runLater(()->{
            removeVBoxFromGrid(oldPosition, newPosition);
            addVBoxToGrid(newPosition);
            System.out.println(map);
            if(actualizeGrip()){
                System.out.println("map have been redrawn");
                System.out.println("");
            }
        });
    }

    private void addVBoxToGrid(Vector2d position){
        VBox newVBox = new GuiElementBox((IMapElement)(map.objectAt(position))).getVBox();
        if (map.objectAt(position) instanceof Animal)
            vBoxMapAnimals.put(position, newVBox);
        if (map.objectAt(position) instanceof Grass)
            vBoxMapGrasses.put(position, newVBox);

        Vector2d positionOnGrid = new Vector2d(position.x,-position.y).add(translationVector);
        gridPane.add(newVBox, positionOnGrid.x + 1, positionOnGrid.y + 1, 1,1);
    }
    private void removeVBoxFromGrid(Vector2d key, Vector2d position){
        if (map.objectAt(position) instanceof Animal) {
            gridPane.getChildren().remove(vBoxMapAnimals.remove(key));
        }
        else if (map.objectAt(position) instanceof Grass){
            gridPane.getChildren().remove(vBoxMapGrasses.remove(key));
        }
        if (map.objectAt(position) instanceof Grass){
            gridPane.getChildren().add(vBoxMapGrasses.get(key));
        }
    }
    private void drawAxis(){
        Label label = new Label("y\\x");
        label.setAlignment(Pos.CENTER);
        this.gridPane.add(label,0,0,1,1);
        GridPane.setHalignment(label, HPos.CENTER);
        label.setMinSize(sizeOfCell, sizeOfCell);
        for (int x = 0; x <= this.width; x++) {
            label = new Label((x - this.translationVector.x) + "");
            this.gridPane.add(label, x + 1, 0, 1, 1);
            GridPane.setHalignment(label, HPos.CENTER);
            label.setAlignment(Pos.CENTER);
            label.setMinSize(sizeOfCell, sizeOfCell);
        }
        for (int y = 0; y <= this.height; y++) {
            label = new Label((this.translationVector.y - y) + "");
            this.gridPane.add(label, 0, y + 1, 1, 1);
            label.setAlignment(Pos.CENTER);
            GridPane.setHalignment(label, HPos.CENTER);
            label.setMinSize(sizeOfCell, sizeOfCell);
        }
    }
    public boolean actualizeGrip(){
        if (map instanceof RectangularMap) return false;
        if (map instanceof GrassField){
            int prevWidth = this.width;
            int prevHeight = this.height;
            Vector2d lowerLeft = ((GrassField)map).getLowerLeft();
            Vector2d upperRight = ((GrassField)map).getUpperRight();
            this.height = upperRight.y - lowerLeft.y;
            this.width = upperRight.x - lowerLeft.x;
            this.translationVector = new Vector2d(-lowerLeft.x, upperRight.y);
            if (! (prevWidth == this.width && prevHeight == this.height)){
                gridPane.getChildren().clear();
                drawAxis();
                fillGrid();
                gridPane.setGridLinesVisible(false);
                gridPane.setGridLinesVisible(true);
            }
            return ! (prevWidth == this.width && prevHeight == this.height);
        }
        return false;
    }
}
