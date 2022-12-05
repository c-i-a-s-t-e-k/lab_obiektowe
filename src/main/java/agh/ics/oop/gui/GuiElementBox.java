package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class GuiElementBox {
    private final ImageLoader loader = new ImageLoader();
    private final VBox vBox = new VBox();

    public GuiElementBox(IMapElement mapElement){
        Image image = loader.loadImage(mapElement.getImageName());
        ImageView imageView = loader.getImageView(image);
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
