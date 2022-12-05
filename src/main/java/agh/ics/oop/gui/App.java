package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import agh.ics.oop.IMapElement;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App extends Application implements IPositionChangeObserver{
//    private final List<Thread> threads = new ArrayList<Thread>();
    private SimulationEngine engine;
    private Map<Vector2d, VBox> vBoxMapAnimals;
    private Map<Vector2d, VBox> vBoxMapGrasses;
    private GridPane gridPane;
    private IWorldMap map;
    private int height;
    private int width;
    private final int sizeOfCell = 40;
    private Vector2d translationVector;

    @Override
    public void init() throws Exception {

        //        RectangularMap map = new RectangularMap(5,5);
        GrassField map = new GrassField(5);
        this.map = map;
        this.vBoxMapAnimals = map.getVBoxAnimals();
//        this.vBoxMapGrasses = null;
        this.vBoxMapGrasses = map.getVBoxGrasses();
        this.gridPane = new GridPane();
        this.translationVector = new Vector2d(-map.getLowerLeft().x, map.getUpperRight().y);
        this.height = map.getUpperRight().y - map.getLowerLeft().y;
        this.width = map.getUpperRight().x - map.getLowerLeft().x;

        this.gridPane.setGridLinesVisible(true);
        this.gridPane.setAlignment(Pos.CENTER);


        //        String[] stringMoves = System.out.println(getParameters().getRaw());
        String[] stringMoves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f", "r"};
        MoveDirection[] directions = OptionsParser.parse(stringMoves);

        fillGrid();
        Vector2d[] positions = {new Vector2d(-2, -2)};
        SimulationEngine engineWithButton = new SimulationEngine(this.map, positions, this);
        actualizeGrip();

        for(Vector2d position : positions){
            addVBoxToGrid( position);
        }
        drawAxis();

        this.engine = engineWithButton;


//        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
//        IEngine engine = new SimulationEngine(directions, map, positions,1000, this);
//        threads.add(new Thread((SimulationEngine)engine));
    }

//    public App(){
////        RectangularMap map = new RectangularMap(5,5);
//        GrassField map = new GrassField(5);
//        this.map = map;
//        this.vBoxMapAnimals = map.getVBoxAnimals();
////        this.vBoxMapGrasses = null;
//        this.vBoxMapGrasses = map.getVBoxGrasses();
//        this.gridPane = new GridPane();
//        this.translationVector = new Vector2d(map.getLowerLeft().x, map.getUpperRight().y);
//        this.height = map.getUpperRight().y - map.getLowerLeft().y;
//        this.width = map.getUpperRight().x - map.getLowerLeft().x;
//
//        this.gridPane.setGridLinesVisible(true);
//        this.gridPane.setAlignment(Pos.CENTER);
//        fillGrid();
//        drawAxis();
//        for (int x = 0; x <= this.height + 1; x++)
//            this.gridPane.getRowConstraints().add(new RowConstraints(sizeOfCell));
//        for (int y = 0; y <= this.width + 1; y++)
//            this.gridPane.getColumnConstraints().add(new ColumnConstraints(sizeOfCell));
//    }

    public void start(Stage primaryStage) throws Exception {
        HBox inputBox = new HBox();
        VBox mainBox = new VBox();
        Button startButton = new Button("start");
        TextField inputText = new TextField();

        inputBox.getChildren().add(startButton);
        inputBox.getChildren().add(inputText);
        inputBox.setAlignment(Pos.CENTER);

        mainBox.getChildren().add(gridPane);
        mainBox.getChildren().add(inputBox);
        Scene scene = new Scene(mainBox, 400, 400);



        primaryStage.setScene(scene);
        primaryStage.show();


        startButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                engine.setMoves(OptionsParser.parse(inputText.getText().split(" ")));
                (new Thread(engine)).start();
            }
        });
//        for (Thread thread : threads){
//            thread.start();
//        }
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        Platform.runLater(()->{
            removeVBoxFromGrid(oldPosition, newPosition);
            addVBoxToGrid(newPosition);
            System.out.println(map);
            if(actualizeGrip()){
                System.out.println("map have been redrawn");
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
    private boolean actualizeGrip(){
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

    private void fillGrid(){
        for (Vector2d key : vBoxMapAnimals.keySet()){
            Vector2d positionOnGrid = new Vector2d(key.x,-key.y).add(translationVector);
            gridPane.add(vBoxMapAnimals.get(key), positionOnGrid.x + 1, positionOnGrid.y + 1, 1,1);
        }
        for (Vector2d key : vBoxMapGrasses.keySet()){
            if (! vBoxMapAnimals.containsKey(key)){
                Vector2d positionOnGrid = new Vector2d(key.x,-key.y).add(translationVector);
                gridPane.add(vBoxMapGrasses.get(key), positionOnGrid.x + 1, positionOnGrid.y + 1, 1,1);
            }
        }
    }
}
