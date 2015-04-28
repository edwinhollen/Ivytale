package org.edwinh.ivytale.components;

import org.edwinh.ivytale.Component;

import java.time.Instant;

/**
 * Created by fubar on 4/28/15.
 */
public class AnimationComponent extends Component {
    public String name = null;
    public int currentFrame = 0;
    public long lastFrame = Instant.now().toEpochMilli();
    public AnimationComponent(String name){
        this.name = name;
    }
}
