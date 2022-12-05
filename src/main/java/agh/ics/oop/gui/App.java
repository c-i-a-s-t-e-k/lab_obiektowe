package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class App extends Application {
    private SimulationEngine engine;
    private MapGridManager manager;
    private IWorldMap map;


    @Override
    public void init(){
//        RectangularMap map = new RectangularMap(5,5);
        GrassField map = new GrassField(5);
        this.map = map;

        this.manager = new MapGridManager(new GridPane(), map);

        Vector2d[] positions = {new Vector2d(-2, -2)};
        SimulationEngine engineWithButton = new SimulationEngine(this.map, positions);
        manager.actualizeGrip();

        for(Vector2d position : positions){
            manager.place((Animal) map.objectAt(position));
        }

        this.engine = engineWithButton;
    }

    public void start(Stage primaryStage){
        HBox inputBox = new HBox();
        VBox mainBox = new VBox();
        Button startButton = new Button("start");
        TextField inputText = new TextField();

        inputBox.getChildren().add(startButton);
        inputBox.getChildren().add(inputText);
        inputBox.setAlignment(Pos.CENTER);

        mainBox.getChildren().add(manager.getGrid());
        mainBox.getChildren().add(inputBox);
        Scene scene = new Scene(mainBox, (manager.getWidth() + 3) * manager.getCellSize()
                , (manager.getHeight() + 2) * manager.getCellSize() + 50);

        primaryStage.setScene(scene);
        primaryStage.show();

        startButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                engine.setMoves(OptionsParser.parse(inputText.getText().split(" ")));
                (new Thread(engine)).start();
            }
        });
    }


}
