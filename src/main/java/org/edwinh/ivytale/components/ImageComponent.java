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
    public boolean flipHorizontal = false;
    public boolean flipVertical = false;

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
                if(pixel.getAlpha() < 1){
                    continue;
                }
                Color newFillColor;
                if(pixel.equals(keyColor)){
                    newFillColor = new Color(fillColor);
                }else if(respectShading &&
                        (keyColor.getGreen() > 0 && pixel.getGreen() > 0) ||
                        (keyColor.getRed() > 0 && pixel.getRed() > 0) ||
                        (keyColor.getBlue() > 0 && pixel.getBlue() > 0)
                        ){
                    int adjRed = 0;
                    int adjGreen = 0;
                    int adjBlue = 0;
                    if(keyColor.getGreen() > 0 && pixel.getGreen() > 0){
                        adjGreen = 255 - pixel.getGreen();
                    }else if(keyColor.getRed() > 0 && pixel.getRed() > 0){
                        adjRed = 255 - pixel.getRed();
                    }else if(keyColor.getBlue() > 0 && pixel.getBlue() > 0){
                        adjBlue = 255 - pixel.getRed();
                    }
                    newFillColor = new Color(fillColor.getRed() - adjRed, fillColor.getGreen() - adjGreen, fillColor.getBlue() - adjBlue);
                }else{
                    continue;
                }
                try {
                    this.image.getGraphics().setColor(newFillColor);
                    this.image.getGraphics().fillRect((float) x, (float) y, 1, 1);
                } catch (SlickException e1) {
                    e1.printStackTrace();
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

    public Image getImage(){
        return this.image.getFlippedCopy(flipHorizontal, flipVertical);
    }
}
