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
    Simulation simulation;  // modyfikator dostępu?
    private GridPane grid;
    private LineChart<Number, Number> lineChart;

    public SimulationView(Config config) {
        this.config = config;
        this.simulation = new Simulation(this, config);
    }


    private void render_refresh(IWorldMap map) {
        // clean
        for (var e : grid.getChildren()) {
            grid.getChildren().remove(e);
        }

        for (int x = 0; x < config.width; x++) {
            for (int y = 0; y < config.height; y++) {
                Vector2d position = new Vector2d(x, y);
                if (map.isOccupied(position)) {
                    IMapElement[] elements = map.elementsAt(position);
                    for (var e : elements) {
                        grid.add(e.get_representation(), x, y);
                    }
                    System.out.println("Drawing at " + position.toString());
                } else {
                    grid.add(new Circle(), x, y);
                }
            }
        }
    }

    public void run() {
        Stage stage = new Stage();

        this.grid = new GridPane();

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        this.lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Stats");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("Plant population");
        //populating the series with data
        series.getData().add(new XYChart.Data(1, 23));
        series.getData().add(new XYChart.Data(2, 14));
        series.getData().add(new XYChart.Data(3, 15));
        series.getData().add(new XYChart.Data(4, 24));
        series.getData().add(new XYChart.Data(5, 34));
        series.getData().add(new XYChart.Data(6, 36));
        series.getData().add(new XYChart.Data(7, 22));
        series.getData().add(new XYChart.Data(8, 45));
        series.getData().add(new XYChart.Data(9, 43));
        series.getData().add(new XYChart.Data(10, 17));
        series.getData().add(new XYChart.Data(11, 29));
        series.getData().add(new XYChart.Data(12, 25));

//        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
        lineChart.setMaxWidth(Double.MAX_VALUE);
        lineChart.setMaxHeight(Double.MAX_VALUE);
        var stats = new Label("Label");
        var vbox = new VBox(lineChart, stats);
        vbox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        vbox.setAlignment(Pos.CENTER);

        var hbox = new HBox(vbox, grid);
        hbox.setAlignment(Pos.CENTER);
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
