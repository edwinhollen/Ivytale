package org.edwinh.ivytale.components;

import org.edwinh.ivytale.Component;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Fubar on 4/19/2015.
 */
public class ImageComponent extends Component {
    public Image image;
    public int offsetX = 0;
    public int offsetY = 0;

    public ImageComponent(String image, int offsetX, int offsetY) {
        try {
            this.image = new Image(image);
            this.image.setFilter(Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public ImageComponent(String image){
        this(image, 0, 0);
    }

    // Constructors for recoloring
    public ImageComponent(String image, int offsetX, int offsetY, Color keyColor, Color fillColor, boolean respectShading){
        this(image, offsetX, offsetY);
        // recolor
        for(int x = 0; x < this.image.getWidth(); x++){
            for(int y = 0; y < this.image.getHeight(); y++){
                Color pixel = this.image.getColor(x, y);
                if(pixel.equals(keyColor)){
                    try {
                        this.image.getGraphics().setColor(fillColor);
                        this.image.getGraphics().fillRect((float) x, (float) y, 1, 1);
                    } catch (SlickException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
        try {
            this.image.getGraphics().flush();
        } catch (SlickException e1) {
            e1.printStackTrace();
        }
    }

    public ImageComponent(String image, Color keyColor, Color fillColor, boolean respectShading) {
        this(image, 0, 0, keyColor, fillColor, respectShading);
    }

    public ImageComponent(String image, Color keyColor, Color fillColor){
        this(image, 0, 0, keyColor, fillColor, false);
    }


}
