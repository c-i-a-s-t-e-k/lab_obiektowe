package agh.ics.darvin.gui;

import agh.ics.darvin.config.Config;
import agh.ics.darvin.InvalidConfigException;
import agh.ics.darvin.enums.MutationType;
import agh.ics.darvin.enums.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


public class LauncherApp extends Application {
    private NumberText widthField;
    private NumberText heightField;
    private ComboBox worldBorderVariant;
    private ComboBox plantGrowthVariant;
    private NumberText startingGrass;
    private NumberText energyPerGrass;
    private NumberText plantsGrowingPerDay;
    private NumberText startingAnimalsNum;
    private NumberText animalStartingEnergy;
    private NumberText minMatingEnergy;
    private NumberText childStartingEnergy;
    private NumberText minNumMutations;
    private NumberText maxNumMutations;
    private ComboBox mutationVariant;
    private NumberText genomeLength;
    private ComboBox movementVariant;
    private NumberText sleepTime;

    private static <T extends Node> T add_input(GridPane grid, String title, int row, T input_form) {
        Label widthLabel = new Label(title);
        grid.add(widthLabel, 0, row);
        grid.add(input_form, 1, row);
        return input_form;
    }

    private static ComboBox add_combo_box(GridPane grid, String title, int row, Collection<String> col) {
        ObservableList<String> worldOptionStrings =
                FXCollections.observableArrayList(
                        col
                );
        var comboBox = new ComboBox(worldOptionStrings);
        comboBox.setValue(col.toArray()[0]);
        add_input(grid, title, row, comboBox);
        return comboBox;
    }

    private void add_header(GridPane grid, String title, Font font, int row) {
        Text scene_title = new Text(title);
        scene_title.setFont(font);
        grid.add(scene_title, 0, row, 2, 1);
    }

    private Config generateConfig() throws InvalidConfigException {
        return new Config(this.widthField.getInt(),
                this.heightField.getInt(),
                ForestType.fromString(this.plantGrowthVariant.getValue().toString()),
                BoundaryType.fromString(this.worldBorderVariant.getValue().toString()),
                this.startingGrass.getInt(),
                this.energyPerGrass.getInt(),
                this.plantsGrowingPerDay.getInt(),
                this.startingAnimalsNum.getInt(),
                this.animalStartingEnergy.getInt(),
                this.minMatingEnergy.getInt(),
                this.childStartingEnergy.getInt(),
                this.minNumMutations.getInt(),
                this.maxNumMutations.getInt(),
                MutationType.fromString(this.mutationVariant.getValue().toString()),
                this.genomeLength.getInt(),
                BehaviourType.fromString(this.movementVariant.getValue().toString()),
                this.sleepTime.getInt()
        );
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("DarwinianSim");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        var header_font = Font.font("Tahoma", FontWeight.NORMAL, 20);
        int rowIndex = 0;

        Scene map_properties_scene = new Scene(grid);

        add_header(grid, "Map Properties", header_font, rowIndex++);
        this.widthField = add_input(grid, "Width", rowIndex++, new NumberText(30));
        this.heightField = add_input(grid, "Height", rowIndex++, new NumberText(30));
        this.worldBorderVariant = add_combo_box(grid, "World border variant", rowIndex++, Arrays.stream(BoundaryType.values()).map(e -> e.toString()).collect(Collectors.toList()));
        add_header(grid, "Grass properties", header_font, rowIndex++);
        this.startingGrass = add_input(grid, "Starting grass", rowIndex++, new NumberText(10));
        this.energyPerGrass = add_input(grid, "Energy gained per grass", rowIndex++, new NumberText(5));
        this.plantsGrowingPerDay = add_input(grid, "Grass growing per day", rowIndex++, new NumberText(10));
        this.plantGrowthVariant = add_combo_box(grid, "Plant growth variant", rowIndex++, Arrays.stream(ForestType.values()).map(e -> e.toString()).collect(Collectors.toList()));
        add_header(grid, "Animal properties", header_font, rowIndex++);
        this.startingAnimalsNum = add_input(grid, "Starting animals", rowIndex++, new NumberText(10));
        this.animalStartingEnergy = add_input(grid, "Animal starting energy", rowIndex++, new NumberText(10));
        this.minMatingEnergy = add_input(grid, "Minimum mating energy", rowIndex++, new NumberText(10));
        this.childStartingEnergy = add_input(grid, "Child starting energy", rowIndex++, new NumberText(10));
        this.minNumMutations = add_input(grid, "Minimum number of mutations", rowIndex++, new NumberText(10));
        this.maxNumMutations = add_input(grid, "Maximum number of mutations", rowIndex++, new NumberText(10));
        this.mutationVariant = add_combo_box(grid, "Mutation variant", rowIndex++, Arrays.stream(MutationType.values()).map(e -> e.toString()).collect(Collectors.toList()));
        this.genomeLength = add_input(grid, "Animal genome length", rowIndex++, new NumberText(10));
        this.movementVariant = add_combo_box(grid, "Movement variant", rowIndex++, Arrays.stream(BehaviourType.values()).map(e -> e.toString()).collect(Collectors.toList()));
        this.sleepTime = add_input(grid, "Sleep after every step [ms]", rowIndex++, new NumberText(100));

        var startButton = new Button("Start simulation");
        HBox box = new HBox(startButton);
        box.setAlignment(Pos.CENTER);
        grid.add(box, 0, rowIndex++, 2, 1);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    var config = generateConfig();
                    SimulationView sim_view = new SimulationView(config);
                    sim_view.run();
                } catch (InvalidConfigException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid config");
                    alert.setHeaderText("Configuration is invalid due to:");
                    alert.setContentText(ex.getMessage());
                    alert.showAndWait();
                }
            }
        };
        startButton.setOnAction(event);

//        PlantVariant.values();
        primaryStage.setScene(map_properties_scene);

        primaryStage.show();
    }
}
