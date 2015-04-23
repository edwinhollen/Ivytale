package org.edwinh.ivytale.components;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AnimationFrame{
    public ImageComponent image;
    public int offsetX = 0;
    public int offsetY = 0;
    public int delay = 10;

    public AnimationFrame(String image, int delay, int offsetX, int offsetY) {
        this.image = new ImageComponent(image, offsetX, offsetY);
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.delay = delay;
    }

    public AnimationFrame(String image, int delay) {
        this(image, delay, 0, 0);
    }
}
