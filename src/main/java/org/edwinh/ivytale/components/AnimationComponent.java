package org.edwinh.ivytale.components;

import org.edwinh.ivytale.Component;
import org.newdawn.slick.Image;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Created by fubar on 4/23/15.
 */
public class AnimationComponent extends Component {
    public int currentFrame = 0;
    public AnimationFrame[] frames = new AnimationFrame[0];
    public long lastFrame = Instant.now().toEpochMilli();
    public AnimationComponent(AnimationFrame[] frames){
        this.frames = frames;
    }
}

