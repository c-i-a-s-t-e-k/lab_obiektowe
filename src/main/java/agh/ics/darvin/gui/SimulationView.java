package agh.ics.darvin.gui;

import agh.ics.darvin.*;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class SimulationView implements IMapUpdateObserver {
    private boolean paused = false;
    private final Config config;
    Simulation simulation;
    private GridPane grid;
    private LineChart<Number, Number> lineChart;
    private XYChart.Series series;

    public SimulationView(Config config) {
        this.config = config;
        this.simulation = new Simulation(this, config);
    }

    private void render_refresh(IWorldMap map) {
        // clean
        while(grid.getChildren().size() > 0) {
            grid.getChildren().remove(0);
        }

        for (int x = 0; x < config.width; x++) {
            for (int y = 0; y < config.height; y++) {
                Vector2d position = new Vector2d(x, y);
                if (map.isOccupied(position)) {
                    IMapElement[] elements = map.elementsAt(position);
                    for (var e : elements) {
                        grid.add(e.get_representation(), x, y);
                    }
                }
                else {
                    grid.add(new Circle(), x, y);
                }
            }
        }

        series.getData().add(new XYChart.Data(simulation.getDay(), simulation.getAnimals().size()));
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
        this.series = new XYChart.Series();
        series.setName("Animal population");


        lineChart.getData().add(series);
//        lineChart.setMaxWidth(Double.MAX_VALUE);
//        lineChart.setMaxHeight(Double.MAX_VALUE);
        var stats = new Label("Label");
        var vbox = new VBox(lineChart, stats);
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
    }

    @Override
    public void mapChanged(IWorldMap map) {
        Platform.runLater(() -> {
            render_refresh(map);
        });
    }

}
