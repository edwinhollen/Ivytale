package org.edwinh.ivytale.systems;

import org.edwinh.ivytale.Entity;
import org.edwinh.ivytale.EntitySystem;
import org.edwinh.ivytale.components.AnimationComponent;
import org.edwinh.ivytale.components.ImageComponent;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Arrays;

/**
 * Created by fubar on 4/23/15.
 */
public class AnimationSystem extends EntitySystem {
    @Override
    public void update(Entity[] entities, GameContainer gc, int dt) {
        for(Entity e : entities){
            long now = Instant.now().getEpochSecond();
            AnimationComponent ac = ((AnimationComponent) e.getComponentByClass(AnimationComponent.class));
            ImageComponent ic = ((ImageComponent) e.getComponentByClass(ImageComponent.class));
            if(now >= ac.lastFrame + ac.frames[ac.currentFrame].delay) {
                int nextFrame = ac.currentFrame == ac.frames.length ? 0 : ac.currentFrame + 1;
                ((AnimationComponent) e.getComponentByClass(AnimationComponent.class)).lastFrame = now;
                e.removeComponentByClass(ImageComponent.class);
                e.addComponent(ac.frames[ac.currentFrame].image);
            }
        }
    }

    @Override
    public void render(Entity[] entities, GameContainer gc, Graphics g) {

    }

    @Override
    public Class[] getAcceptedComponents() {
        return new Class[]{
                ImageComponent.class,
                AnimationComponent.class
        };
    }
}
