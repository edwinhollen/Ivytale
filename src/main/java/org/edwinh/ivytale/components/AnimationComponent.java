package org.edwinh.ivytale.components;

import org.edwinh.ivytale.Component;

import java.time.Instant;

/**
 * Created by fubar on 4/28/15.
 */
public class AnimationComponent extends Component {
    public String name;
    public int currentFrame;
    public long lastFrame;
    public void change(String name){
        this.name = name;
        this.currentFrame = 0;
        this.lastFrame = Instant.now().toEpochMilli();
    }
    public AnimationComponent(String name){
        this.name = name;
        this.currentFrame = 0;
        this.lastFrame = Instant.now().toEpochMilli();
    }
}
