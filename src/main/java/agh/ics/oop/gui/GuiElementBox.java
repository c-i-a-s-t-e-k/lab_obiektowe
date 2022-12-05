package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class GuiElementBox {
    private final Image image;
    private final VBox vBox = new VBox();

    public GuiElementBox(IMapElement mapElement){
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(mapElement.getImageName())));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(imageView);
        vBox.getChildren().add(new Label(mapElement.getPosition().toString()));
    }

    public VBox getVBox(){
        return vBox;
    }
}