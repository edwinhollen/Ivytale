package org.edwinh.ivytale.components;

import org.edwinh.ivytale.Component;
import org.newdawn.slick.Image;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fubar on 4/23/15.
 */
public class AnimationComponent extends Component {
    public int currentFrame = 0;
    public long lastFrame = Instant.now().toEpochMilli();
    public int delay = 5000;
    public boolean looping = true;
    public List<ImageComponent> frames = new ArrayList<>();
    public AnimationComponent(List<ImageComponent> frames, int delay, boolean looping){
        this.frames = frames;
        this.delay = delay;
        this.looping = looping;
    }
    public AnimationComponent(List<ImageComponent> frames, int delay){
        this(frames, delay, true);
    }
}

