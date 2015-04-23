package org.edwinh.ivytale.components;

import org.edwinh.ivytale.Component;
import org.newdawn.slick.Image;

/**
 * Created by fubar on 4/23/15.
 */
public class AnimationComponent extends Component {
    public Frame[] frames = new Frame[0];
    public class Frame{
        public Image image;
        public int offsetX = 0;
        public int offsetY = 0;
    }
}
