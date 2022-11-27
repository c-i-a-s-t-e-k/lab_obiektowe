package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application{
    public void start(Stage primaryStage) throws Exception {
        init();

        String[] stringMoves = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(stringMoves);
        GrassField map = new GrassField(5);
        Vector2d[] positions = {new Vector2d(2, 2)};//, new Vector2d(3, 4)};
        IEngine engine = new SimulationEngine(directions, map, positions);
//        System.out.println(map);
        engine.run();
//        System.out.println(map);

        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();

//        Label label = new Label("Zwierzak");
//        Scene scene = new Scene(label, 0, 0);

        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
//        GridPane.setHalignment();
        for (int x = 0; x <= upperRight.x - lowerLeft.x; x++)
            gridPane.getRowConstraints().add(new RowConstraints(20));
        for (int y = 0; y <= upperRight.y - lowerLeft.y; y++)
            gridPane.getColumnConstraints().add(new ColumnConstraints(20));

        for (int x = 0; x <= upperRight.x - lowerLeft.x; x++){
            int xOnMap = x + lowerLeft.x;
            for (int y = 0; y <= upperRight.y - lowerLeft.y; y++){
                int yOnMap = y + lowerLeft.y;
                System.out.println(x + " " + y);
                Object mapElement = map.objectAt(new Vector2d(xOnMap,yOnMap));
                if(mapElement == null){
                    gridPane.add(new Label(" "),x,y,1,1);
                }
                else {
                    gridPane.add(new Label(mapElement.toString()), x, y, 1,1);
                }
            }
        }

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}



