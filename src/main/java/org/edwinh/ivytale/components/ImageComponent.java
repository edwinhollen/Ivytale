package org.edwinh.ivytale.components;

import org.edwinh.ivytale.Component;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Fubar on 4/19/2015.
 */
public class ImageComponent extends Component {
    public Image image;
    public int offsetX = 0;
    public int offsetY = 0;
    public ImageComponent(){}
    public ImageComponent(String image){
        this(image, 0, 0);
    }

    public ImageComponent(String image, int offsetX, int offsetY) {
        try {
            this.image = new Image(image);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
}
