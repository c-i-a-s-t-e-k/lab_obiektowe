package agh.ics.darvin.gui;

import agh.ics.darvin.Config;
import agh.ics.darvin.Simulation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimulationView {
    static public void run(Config config) {
        Stage stage = new Stage();

        Simulation simulation = new Simulation();

        var grid = new GridPane();

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        //creating the chart
        final LineChart<Number,Number> lineChart =
                new LineChart<Number,Number>(xAxis,yAxis);

        lineChart.setTitle("Stock Monitoring, 2010");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
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
        stage.setTitle("Simulation");
        stage.setScene(new Scene(hbox, 450, 450));
        stage.showAndWait();
    }

}