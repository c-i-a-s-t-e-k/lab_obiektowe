package gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application{
    private final GridPane gridPane;
    private final IWorldMap map;
    private int height;
    private int width;
    private Vector2d translationVector;

    public App(){
//        RectangularMap map = new RectangularMap(5,5);
        GrassField map = new GrassField(5);
        this.map = map;
        this.gridPane = new GridPane();
        this.translationVector = map.getLowerLeft();
        this.height = map.getUpperRight().y;
        this.width = map.getUpperRight().x;
    }

    private void actualizeDimensions(){
        if (map instanceof GrassField){
            Vector2d lowerLeft = ((GrassField)map).getLowerLeft();
            Vector2d upperRight = ((GrassField)map).getUpperRight();
            this.height = upperRight.y - lowerLeft.y;
            this.width = upperRight.x - lowerLeft.x;
            this.translationVector = ((GrassField) map).getLowerLeft();
        }
    }

    private void prepareGrid(){
        this.gridPane.setGridLinesVisible(true);
        for (int x = 0; x <= this.height + 1; x++)
            this.gridPane.getRowConstraints().add(new RowConstraints(20));
        for (int y = 0; y <= this.width + 1; y++)
            this.gridPane.getColumnConstraints().add(new ColumnConstraints(20));

        this.gridPane.add(new Label("y\\x"),0,0,1,1);
        for (int x = 0; x <= this.width; x++)
            this.gridPane.add(new Label((x + this.translationVector.x) + ""),x+1,0,1,1);
        for (int y = 0; y <= this.height; y++)
            this.gridPane.add(new Label((this.translationVector.y + height - y) + ""),0,y+1,1,1);
    }

    private void fillGrid(){
        for (int x = 0; x <= width; x++){
            for (int y = 0; y <= height; y++){
                Object mapElement = map.objectAt(new Vector2d(x,y).add(translationVector));
                if(mapElement != null){
                    gridPane.add(new Label(mapElement.toString()), x + 1, y + 1, 1,1);
                }
            }
        }
    }
    private void startEngine(String[] stringMoves){
        MoveDirection[] directions = new OptionsParser().parse(stringMoves);
        Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        actualizeDimensions();
        System.out.println(map);
    }
    public void start(Stage primaryStage) throws Exception {
        //        String[] stringMoves = System.out.println(getParameters().getRaw());
        String[] stringMoves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};

        startEngine(stringMoves);
        prepareGrid();
        fillGrid();
        System.out.println(width + " " + height);
        Scene scene = new Scene(gridPane, 20 * (width + 2), 20 * (height + 2));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
