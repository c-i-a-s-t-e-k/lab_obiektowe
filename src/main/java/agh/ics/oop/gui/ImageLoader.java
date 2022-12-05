package agh.ics.oop.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ImageLoader {
    final private Map<String, Image> images = new HashMap<>();
    final private Map<Image, ImageView> imagesViews = new HashMap<>();

    public Image loadImage(String imagineName){
        if(! images.containsKey(imagineName)){
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagineName)));
            imagesViews.put(image, new ImageView(image));
            images.put(imagineName, image);
        }
        return images.get(imagineName);
    }

    public ImageView getImageView(Image image) throws IllegalArgumentException{
        if(! imagesViews.containsKey(image)) throw new IllegalArgumentException("given image:" + image + " do not loaded");
        return imagesViews.get(image);
    }
}
