package agh.ics.darvin.gui;

import agh.ics.darvin.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SimulationView implements IMapUpdateObserver, CanSetAnimal {
    private boolean paused = false;
    private final Config config;
    Simulation simulation;
    private GridPane grid;
    private LineChart<Number, Number> lineChart;
    private XYChart.Series animal_count_series;
    private XYChart.Series plant_count_series;
    private XYChart.Series empty_field_series;
    private Animal tracked_animal = null;
    private Label animalText;

    public SimulationView(Config config) {
        this.config = config;
        this.simulation = new Simulation(this, config);
    }

    private void render_refresh(IWorldMap map) {
        // clean
        while (grid.getChildren().size() > 0) {
            grid.getChildren().remove(0);
        }

        int n_drawn = 0;
        int empty_fields = config.width * config.height;
        for (int x = 0; x < config.width; x++) {
            for (int y = 0; y < config.height; y++) {
                Vector2d position = new Vector2d(x, y);
                if (map.isOccupied(position)) {
                    IMapElement[] elements = map.elementsAt(position);
                    for (var e : elements) {
                        grid.add(e.get_representation(this), x, y);
                        n_drawn++;
                    }
                } else {
                    empty_fields--;
                    grid.add(new Circle(), x, y);
                }
            }
        }

        animal_count_series.getData().add(new XYChart.Data(simulation.getDay(), simulation.getAnimals().size()));
        plant_count_series.getData().add(new XYChart.Data(simulation.getDay(), n_drawn - simulation.getAnimals().size()));
        empty_field_series.getData().add(new XYChart.Data(simulation.getDay(), empty_fields));
        if(tracked_animal!= null)
            animalText.setText(tracked_animal.getInfo());
    }

    public void run() {
        Stage stage = new Stage();

        this.grid = new GridPane();
        grid.setGridLinesVisible(true);

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Day number");
        //creating the chart
        this.lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Stats");
        //defining a series
        animal_count_series = new XYChart.Series();
        animal_count_series.setName("Animal population");
        lineChart.getData().add(animal_count_series);

        plant_count_series = new XYChart.Series();
        plant_count_series.setName("Plant count");
        lineChart.getData().add(plant_count_series);

        empty_field_series = new XYChart.Series();
        empty_field_series.setName("Empty fields");
        lineChart.getData().add(empty_field_series);


//        lineChart.setMaxWidth(Double.MAX_VALUE);
//        lineChart.setMaxHeight(Double.MAX_VALUE);
        var stats = new Label("Green rectangles are grass\nCircles are animals, the more energy an animal has, the brighter it is");
        var pauseButton = new Button("Pause simulation");
        this.animalText = new Label("");
        var vbox = new VBox(lineChart, stats, pauseButton, animalText);
        vbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        vbox.setAlignment(Pos.CENTER);

        var hbox = new HBox(vbox, grid);
        hbox.setAlignment(Pos.CENTER);
        grid.setAlignment(Pos.CENTER);
        stage.setTitle("Simulation");
        stage.setScene(new Scene(hbox, 450, 450));
        stage.show();
//        engine.moveDelay = 300; // in ms
        Thread engineThread = new Thread(simulation);
        engineThread.start();
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (paused) {
                    engineThread.resume();
                    paused = false;
                    pauseButton.setText("Pause");
                } else {
                    engineThread.suspend();
                    paused = true;
                    pauseButton.setText("Suspend");
                }
            }
        };
        pauseButton.setOnAction(event);

        stage.setOnCloseRequest(new EventHandler() {
            @Override
            public void handle(Event event) {
                engineThread.interrupt();
                try {
                    engineThread.join();
                } catch (InterruptedException e) {
                    //Ignore ;
                }
            }
        });
    }

    @Override
    public void mapChanged(IWorldMap map) {
        Platform.runLater(() -> {
            render_refresh(map);
        });
    }

    @Override
    public void setAnimal(Animal animal) {
        tracked_animal = animal;
    }
}
