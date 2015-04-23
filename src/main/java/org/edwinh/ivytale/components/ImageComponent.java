package org.edwinh.ivytale.components;

import org.edwinh.ivytale.Component;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Fubar on 4/19/2015.
 */
public class ImageComponent extends Component {
    public Image image;
    public ImageComponent(String imageUrl){
        try {
            this.image = new Image(imageUrl);
            this.image.setFilter(Image.FILTER_NEAREST);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
